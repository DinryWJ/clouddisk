package com.dinry.clouddisk.api;

import com.dinry.clouddisk.ApiResponse;
import com.dinry.clouddisk.common.FileSizeUtil;
import com.dinry.clouddisk.common.MimeTypeUtil;
import com.dinry.clouddisk.dto.FileContentInfo;
import com.dinry.clouddisk.dto.FileInfo;
import com.dinry.clouddisk.dto.LoginInfo;
import com.dinry.clouddisk.model.Content;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    @ApiOperation(value = "下载文件")
    @GetMapping("/downloadFiles")
    public void downloadFiles(
            @ApiParam(value = "文件列表", required = false, example = "0") @RequestParam(value = "fs", required = false) List<Integer> fileList,
            @ApiParam(value = "目录列表", required = false, example = "0") @RequestParam(value = "ds", required = false) List<Integer> dirList,
            HttpServletResponse response
    ) {
        LoginInfo info = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        if (info == null) {
            return;
        }
        if ((fileList != null && fileList.size() > 1) || (dirList != null && dirList.size() > 0)) {
            //多文件或单文件夹打包下载
            List<FileInfo> fileInfos = new ArrayList<>(16);
            if (dirList != null) {
                for (Integer i : dirList) {
                    handleFiles("", i, fileInfos, info.getUserId());
                }
            }
            if (fileList != null) {
                for (Integer i : fileList) {
                    FileInfo fileInfo = fileContentService.getFileInfo(i, info.getUserId());
                    fileInfo.setRelativePath("");
                    fileInfos.add(fileInfo);
                }
            }
            if (fileInfos.size() != 0) {
                response.reset();
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Access-Control-Expose-Headers","Content-Disposition,Content-Length");
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Methods","*");
                try (ZipOutputStream out = new ZipOutputStream(response.getOutputStream()); ServletOutputStream outputStream = response.getOutputStream()) {
                    // 读入需要下载的文件的内容，打包到zip文件
                    response.addHeader("Content-Disposition", "filename=" + "download-" + LocalDateTime.now() + ".zip");
                    response.addHeader("Content-Length", "");
                    for (FileInfo fileInfo : fileInfos) {
                        out.putNextEntry(new ZipEntry(fileInfo.getRelativePath() + fileInfo.getName()));
                        out.write(Files.readAllBytes(Paths.get(fileInfo.getPath())));
                        out.closeEntry();
                    }
                    out.close();
                    outputStream.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        if (fileList != null && fileList.size() == 1) {
            //单文件下载
            FileInfo fileInfo = fileContentService.getFileInfo(fileList.get(0), info.getUserId());
            if (fileInfo != null) {
                response.reset();
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Access-Control-Expose-Headers","Content-Disposition,Content-Length");
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Methods","*");
                try (ServletOutputStream out = response.getOutputStream()) {
                    response.addHeader("Content-Disposition", "filename=" + fileInfo.getName());
                    response.addHeader("Content-Length", "");
                    out.write(Files.readAllBytes(Paths.get(fileInfo.getPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取准备压缩的文件信息
     *
     * @param prefixPath
     * @param contentId
     * @param fileInfos
     * @param userId
     * @return
     */
    private void handleFiles(String prefixPath, Integer contentId, List<FileInfo> fileInfos, Integer userId) {
        prefixPath = prefixPath + contentService.getContentById(contentId).getName() + "/";
        //获取目录下所有的文件
        List<FileInfo> fileInfoList = contentService.getFileInfoByContentId(contentId);
        for (FileInfo fileInfo : fileInfoList) {
            fileInfos.add(new FileInfo(fileInfo.getName(), fileInfo.getPath(), prefixPath));
        }
        List<Content> contentList = contentService.getContent(contentId, userId);
        for (Content content : contentList) {
            handleFiles(prefixPath, content.getId(), fileInfos, userId);
        }
    }

    @ApiOperation(value = "批量删除文件")
    @PostMapping("/batchDeleteFiles")
    public ResponseEntity<ApiResponse> batchDeleteFiles(
            @ApiParam(value = "文件id列表", required = true, example = "0") @RequestBody List<FileContentInfo> fileContentInfoList
    ) {
        long start = System.currentTimeMillis();
        LoginInfo info = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        FileContent fileContent;
        int effectedNum = 0;
        for (FileContentInfo fileContentInfo : fileContentInfoList) {
            System.out.println(fileContentInfo);
            if (fileContentInfo.getIsFolder()) {
                effectedNum++;
                deleteFolderById(fileContentInfo.getId(), info.getUserId());
            } else {
                fileContent = fileContentService.getFileContentById(fileContentInfo.getId(), info.getUserId());
                if (fileContent != null) {
                    effectedNum += fileContentService.deleteContentFile(fileContentInfo.getId(), info.getUserId());
                    deleteFileById(fileContent.getFileId());
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("删除用时:" + (end - start) + "ms");
        if (effectedNum == fileContentInfoList.size()) {
            return ApiResponse.successResponse("1");
        }
        return ApiResponse.errorResponse("0");
    }

    /**
     * 删除单个文件
     *
     * @param id
     */
    private void deleteFileById(int id) {
        if (id != 0) {
            TFile tFile = fileService.getById(id);
            int eff = 0;
            if (tFile != null) {
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
        }
    }

    /**
     * 递归删除文件夹及下面的所有文件
     *
     * @param id
     * @param userId
     */
    private void deleteFolderById(int id, int userId) {
        contentService.deleteContent(id, userId);
        List<FileContent> fileContents = fileContentService.getFilesByFolderId(id, userId);
        for (FileContent fileContent : fileContents) {
            fileContentService.deleteContentFile(fileContent.getId(), userId);
            deleteFileById(fileContent.getFileId());
        }
        List<Content> contentList = contentService.getContent(id, userId);
        for (Content content : contentList) {
            deleteFolderById(content.getId(), userId);
        }
    }
}
