package com.dinry.clouddisk.dto;

import lombok.Data;

/**
 * @Author: 吴佳杰
 * @Date: 2018/12/12 14:26
 * @Description: 文件下载 dto
 */
@Data
public class FileInfo {
    private Integer id;
    private String name;
    private String path;
    private String relativePath;

    public FileInfo() {
    }

    public FileInfo(String name, String path, String relativePath) {
        this.name = name;
        this.path = path;
        this.relativePath = relativePath;
    }
}
