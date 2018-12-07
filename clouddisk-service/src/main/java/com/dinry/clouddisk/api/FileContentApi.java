package com.dinry.clouddisk.api;

import com.dinry.clouddisk.common.FileSizeUtil;
import com.dinry.clouddisk.common.MimeTypeUtil;
import com.dinry.clouddisk.dto.LoginInfo;
import com.dinry.clouddisk.model.FileContent;
import com.dinry.clouddisk.model.TFile;
import com.dinry.clouddisk.service.ContentService;
import com.dinry.clouddisk.service.FileContentService;
import com.dinry.clouddisk.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:03
 * @Description:
 */
@Slf4j
@Api(value = "目录管理", description = "目录管理")
@RequestMapping("/fileContent")
@RestController
public class FileContentApi {
    private final ContentService contentService;
    private final FileContentService fileContentService;
    private final FileService fileService;
    /**
     * 存储文件夹
     */
    @Value("${diskfolder}")
    private String diskFolder;

    @Autowired
    public FileContentApi(FileContentService fileContentService, ContentService contentService, FileService fileService) {
        this.fileContentService = fileContentService;
        this.contentService = contentService;
        this.fileService = fileService;
    }


    @ApiOperation(value = "保存文件至文件目录中")
    @PostMapping(value = "/saveFileToContent")
    public ResponseEntity<ApiResponse> saveFileToContent(
            @ApiParam(value = "文件id", required = true, example = "0") @RequestParam(value = "fileId", required = true) int fileId,
            @ApiParam(value = "文件名", required = true) @RequestParam(value = "fileName", required = true) String fileName,
            @ApiParam(value = "文件大小", required = true, example = "0") @RequestParam(value = "totalSize", required = true) long totalSize,
            @ApiParam(value = "文件存储目录", required = true) @RequestParam(value = "rootPath", required = true) String rootPath,
            @ApiParam(value = "文件存储目录id", required = true, example = "0") @RequestParam(value = "directoryId", required = true) int directoryId,
            @ApiParam(value = "是否为文件夹", required = true) @RequestParam(value = "directory", required = true) boolean directory,
            @ApiParam(value = "相对路径", required = true) @RequestParam(value = "relativePath", required = true) String relativePath,
            @ApiParam(value = "文件类型", required = true) @RequestParam(value = "fileType", required = true) String fileType
    ) {
        LoginInfo info = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        int eff = 0;
        int parentId = directoryId;
        if (!Objects.equals(relativePath, fileName)) {
            parentId = contentService.saveFolderByRelativePath(relativePath, directoryId, info.getUserId());
        }
        fileName = fileContentService.detectFileNameDuplicate(fileName, parentId);
        eff = fileContentService.saveFileToContent(fileId, fileName, FileSizeUtil.getFileSize(totalSize), parentId, MimeTypeUtil.getExtension(fileType), info.getUserId());
        return ApiResponse.successResponse(eff);
    }

    @ApiOperation(value = "删除文件")
    @PostMapping(value = "/deleteFile")
    @Transactional
    public ResponseEntity<ApiResponse> deleteFile(
            @ApiParam(value = "文件id", required = true, example = "0") @RequestParam(value = "fileContentId", required = true) int fileContentId
    ) {
        LoginInfo info = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        FileContent fileContent = fileContentService.getFileContentById(fileContentId, info.getUserId());
        if (fileContent != null) {
            int effectedNum = fileContentService.deleteContentFile(fileContentId, info.getUserId());
            if (fileContent.getFileId() != 0) {
                TFile tFile = fileService.getById(fileContent.getFileId());
                int eff = 0;
                if (tFile.getId() != 0 && tFile.getRes() > 1) {
                    eff = fileService.decreaseFileRes(tFile.getId(), tFile.getRes());
                }
                if (tFile.getId() != 0 && tFile.getRes() == 1) {
                    try {
                        Files.delete(Paths.get(tFile.getPath()));
                    } catch (IOException e) {
                        log.error("文件删除失败:" + e.getMessage());
                    }
                    eff = fileService.deleteFile(tFile.getId(), tFile.getRes());
                }
            }
            if (effectedNum < 1) {
                throw new RuntimeException("文件删除失败");
            }
            return ApiResponse.successResponse(effectedNum);
        }
        return ApiResponse.errorResponse("删除失败，请刷新重试");
    }
}
