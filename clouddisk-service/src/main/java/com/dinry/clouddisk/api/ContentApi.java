package com.dinry.clouddisk.api;

import com.dinry.clouddisk.model.Content;
import com.dinry.clouddisk.model.FileContent;
import com.dinry.clouddisk.service.ContentService;
import com.dinry.clouddisk.service.FileContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/26 10:23
 * @Description:
 */
@Api(value = "目录管理", description = "目录管理")
@RequestMapping("/content")
@RestController
public class ContentApi {
    private final ContentService contentService;
    private final FileContentService fileContentService;

    @Autowired
    public ContentApi(ContentService contentService, FileContentService fileContentService) {
        this.contentService = contentService;
        this.fileContentService = fileContentService;
    }

    @ApiOperation(value = "获取目录及文件")
    @GetMapping(value = "/getContent")
    public ResponseEntity<ApiResponse> getContent(
            @ApiParam(value = "上级目录id", required = true) @RequestParam(value = "parentId", required = true) int parentId
    ) {
        List<Content> contentList = contentService.getContent(parentId);
        List<FileContent> fileContentList = fileContentService.getFilesByFolderId(parentId);
        Map<String, List> resultMap = new HashMap<>(16);
        resultMap.put("contents", contentList);
        resultMap.put("files", fileContentList);
        return ApiResponse.successResponse(resultMap);
    }
}
