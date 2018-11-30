package com.dinry.clouddisk.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/30 11:41
 */
public class MimeTypeUtil {
    public static String getExtension(String mime) {
        if (mime == null) {
            return "unknown";
        }
        String val;
        switch (mime) {
            case "application/msword": {
                val = "doc";
                break;
            }
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": {
                val = "doc";
                break;
            }

            case "application/pdf": {
                val = "pdf";
                break;
            }

            case "application/vnd.ms-excel": {
                val = "xls";
                break;
            }
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": {
                val = "xls";
                break;
            }

            case "application/vnd.ms-powerpoint": {
                val = "ppt";
                break;
            }
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation": {
                val = "ppt";
                break;
            }

            case "application/zip": {
                val = "zip";
                break;
            }

            case "application/x-msdownload": {
                val = "exe";
                break;
            }

            case "video/mp4": {
                val = "video";
                break;
            }
            case "video/mpeg4": {
                val = "video";
                break;
            }
            case "video/mpg": {
                val = "video";
                break;
            }

            case "audio/mpeg": {
                val = "music";
                break;
            }
            case "audio/wav": {
                val = "music";
                break;
            }
            case "audio/x-ms-wma": {
                val = "music";
                break;
            }

            case "image/gif": {
                val = "image";
                break;
            }
            case "image/x-icon": {
                val = "image";
                break;
            }
            case "image/jpeg": {
                val = "image";
                break;
            }
            case "image/png": {
                val = "image";
                break;
            }

            case "text/html":{
                val = "text";
                break;
            }
            case "text/plain":{
                val = "text";
                break;
            }
            case "text/xml":{
                val = "text";
                break;
            }
            case "application/xml":{
                val = "text";
                break;
            }
            case "application/json":{
                val = "text";
                break;
            }
            case "application/javascript":{
                val = "text";
                break;
            }
            default: {
                val = "unknown";
            }
        }
        return val;
    }

    public static void main(String[] args) {
        Path path = Paths.get("D:\\jypt.sql");
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File content type is : " + contentType);
        System.out.println(getExtension(contentType));
    }
}
