package com.dinry.clouddisk.controller;

import com.dinry.clouddisk.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/12 14:14
 * @Description:
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            uploadService.post(request, new Uploader.UploadListener() {
                @Override
                public void callback(String status, String filename, String original_filename, String identifier, String fileType) {
                    if (status != null) {
                        this.sucess(response, status);
                    }
                }

                private void sucess(HttpServletResponse response, String status) {
                    PrintWriter writer;
                    try {
                        writer = response.getWriter();
                        writer.write(status);
                    } catch (Exception e) {
                        log.error("文件上传失败，原因：" + e.toString());
                    }
                }
            });
        } catch (Exception e) {
            log.error("文件上传失败，原因：" + e.toString());
        }
    }


}