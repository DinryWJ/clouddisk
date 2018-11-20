package com.dinry.clouddisk.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:09
 * @Description:
 */
@Api(value = "文件管理", description = "文件管理")
@RequestMapping("/file")
@RestController
public class FileApi {
}
