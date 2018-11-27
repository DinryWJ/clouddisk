package com.dinry.clouddisk.api;

import com.dinry.clouddisk.common.FileSizeUtil;
import com.dinry.clouddisk.dto.LoginInfo;
import com.dinry.clouddisk.service.FileContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:03
 * @Description:
 */
@Api(value = "目录管理", description = "目录管理")
@RequestMapping("/fileContent")
@RestController
public class FileContentApi {

    private final FileContentService fileContentService;

    @Autowired
    public FileContentApi(FileContentService fileContentService) {
        this.fileContentService = fileContentService;
    }


    @ApiOperation(value = "保存文件至文件目录中")
    @PostMapping(value = "/saveFileToContent")
    public ResponseEntity<ApiResponse> saveFileToContent(
            @ApiParam(value = "文件id", required = true) @RequestParam(value = "fileId", required = true) int fileId,
            @ApiParam(value = "文件名", required = true) @RequestParam(value = "fileName", required = true) String fileName,
            @ApiParam(value = "文件大小", required = true) @RequestParam(value = "totalSize", required = true) long totalSize,
            @ApiParam(value = "文件存储目录", required = true) @RequestParam(value = "rootPath", required = true) String rootPath,
            @ApiParam(value = "文件存储目录id", required = true) @RequestParam(value = "directoryId", required = true) int directoryId,
            @ApiParam(value = "是否为文件夹", required = true) @RequestParam(value = "directory", required = true) boolean directory,
            @ApiParam(value = "文件类型", required = true) @RequestParam(value = "fileType", required = true) String fileType
    ) {
        LoginInfo info = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        int eff = 0;
        if (!directory) {
            fileName = fileContentService.detectFileNameDuplicate(fileName, directoryId);
            eff = fileContentService.saveFileToContent(fileId, fileName, FileSizeUtil.getFileSize(totalSize), directoryId, fileType, info.getUserId());
            if (eff > 0) {
                return ApiResponse.successResponse(eff);
            }
        }
        return ApiResponse.errorResponse("保存失败，请重试");
    }
}
