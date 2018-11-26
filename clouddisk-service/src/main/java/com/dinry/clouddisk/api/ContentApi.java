package com.dinry.clouddisk.api;

import com.dinry.clouddisk.common.FileSizeUtil;
import com.dinry.clouddisk.dto.LoginInfo;
import com.dinry.clouddisk.model.Content;
import com.dinry.clouddisk.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    public ContentApi(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation(value = "获取目录")
    @GetMapping(value = "/getContent")
    public ResponseEntity<ApiResponse> getContent(
            @ApiParam(value = "上级目录id", required = true) @RequestParam(value = "parentId", required = true) int parentId
    ) {
        List<Content> list = contentService.getContent(parentId);
        return ApiResponse.successResponse(list);
    }
}
