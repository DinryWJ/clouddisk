package com.dinry.clouddisk.api;

import com.dinry.clouddisk.service.ContentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:03
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
}
