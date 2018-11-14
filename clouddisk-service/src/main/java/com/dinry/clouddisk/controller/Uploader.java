package com.dinry.clouddisk.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

//https://github.com/simple-uploader/Uploader/blob/develop/samples/Node.js/uploader-node.js
@Slf4j
public class Uploader {
    /**
     * 临时文件夹
     */
    @Value("${temporaryfolder}")
    private String temporaryFolder;
    /**
     * 存储文件夹
     */
    @Value("${diskfolder}")
    private String diskFolder;
    /**
     * 最大文件大小
     */
//    @Value("${maxfilesize}")
    private Long maxFileSize = Long.MAX_VALUE;

    private String getChunkFilename(int chunkNumber, String identifier) {
        return new File(temporaryFolder, "uploader-" + identifier + '.' + chunkNumber).getAbsolutePath();
    }

    private String validateRequest(int chunkNumber, long chunkSize, long totalSize, String identifier, String filename, Integer fileSize) {
        if (chunkNumber == 0 || chunkSize == 0 || totalSize == 0 || identifier.length() == 0 || filename.length() == 0) {
            return "non_uploader_request";
        }
        int numberOfChunks = (int) Math.max(Math.floor(totalSize / (chunkSize * 1.0)), 1);
        if (chunkNumber > numberOfChunks) {
            return "invalid_uploader_request1";
        }

        if (this.maxFileSize != null && totalSize > this.maxFileSize) {
            return "invalid_uploader_request2";
        }

        if (fileSize != null) {
            if (chunkNumber < numberOfChunks && fileSize != chunkSize) {
                return "invalid_uploader_request3";
            }
            if (numberOfChunks > 1 && chunkNumber == numberOfChunks
                    && fileSize != ((totalSize % chunkSize) + chunkSize)) {
                return "invalid_uploader_request4";
            }
            if (numberOfChunks == 1 && fileSize != totalSize) {
                return "invalid_uploader_request5";
            }
        }

        return "valid";
    }

    private int getParamInt(HttpServletRequest req, String key, int def) {
        String value = req.getParameter(key);
        return Optional.of(Integer.parseInt(value)).orElse(def);
    }

    private long getParamLong(HttpServletRequest req, String key, long def) {
        String value = req.getParameter(key);
        return Optional.of(Long.parseLong(value)).orElse(def);
    }

    private String getParamString(HttpServletRequest req, String key, String def) {
        String value = req.getParameter(key);
        return Optional.ofNullable(value).orElse(def);
    }

    public void get(HttpServletRequest req, UploadListener listener) {
        int chunkNumber = this.getParamInt(req, "chunkNumber", 0);
        long chunkSize = this.getParamLong(req, "chunkSize", 0);
        long totalSize = this.getParamLong(req, "totalSize", 0);
        String identifier = this.getParamString(req, "identifier", "");
        String filename = this.getParamString(req, "filename", "");
        if (validateRequest(chunkNumber, chunkSize, totalSize, identifier, filename, null).equals("valid")) {
            String chunkFilename = getChunkFilename(chunkNumber, identifier);
            if (new File(chunkFilename).exists()) {
                listener.callback("found", chunkFilename, filename, identifier, null);
            } else {
                listener.callback("not_found", null, null, null, null);
            }
        } else {
            listener.callback("not_found", null, null, null, null);
        }
    }

    public void post(HttpServletRequest req, UploadListener listener) {
        int chunkNumber = this.getParamInt(req, "chunkNumber", 0);
        long chunkSize = this.getParamLong(req, "chunkSize", 0);
        long totalSize = this.getParamLong(req, "totalSize", 0);
        String identifier = this.getParamString(req, "identifier", "");
        String filename = this.getParamString(req, "filename", "");

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());

        if (multipartResolver.isMultipart(req)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
            // 获取multiRequest 中所有的文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                String name = iter.next();

                MultipartFile file = multiRequest.getFile(name);

                if (file != null && file.getSize() > 0) {
                    String original_filename = file.getOriginalFilename();
                    String validation = validateRequest(chunkNumber, chunkSize, totalSize, identifier, filename,
                            (int) file.getSize());

                    if ("valid".equals(validation)) {
                        String chunkFilename = getChunkFilename(chunkNumber, identifier);

                        if (!Files.exists(Paths.get(chunkFilename))) {
                            try {
                                file.transferTo(new File(chunkFilename));
                            } catch (IOException e) {
                                log.error("上传错误:" + e.toString());
                            }
                        }

                        int currentTestChunk = 1;
                        int numberOfChunks = (int) Math.max(Math.floor(totalSize / (chunkSize * 1.0)), 1);

                        this.testChunkExists(currentTestChunk, chunkNumber, numberOfChunks,
                                chunkFilename, original_filename, identifier, listener, "file");

                    } else {
                        listener.callback(validation, filename, original_filename, identifier, "file");
                    }
                } else {
                    listener.callback("invalid_uploader_request", null, null, null, null);
                }
            }
        }
    }


    private void write(String identifier, File file, UploadOptions options) throws IOException {
        String chunkFilename;
        try (OutputStream writableStream = new FileOutputStream(file)) {
            for (int number = 1; ; number++) {
                chunkFilename = getChunkFilename(number, identifier);
                if (Files.exists(Paths.get(chunkFilename))) {
                    int maxlen = 2048;
                    int len = 0;
                    try (FileInputStream inputStream = new FileInputStream(new File(chunkFilename))) {
                        byte[] buff = new byte[maxlen];
                        while ((len = inputStream.read(buff, 0, maxlen)) > 0) {
                            writableStream.write(buff, 0, len);
                        }
                    }
                } else {
                    options.end = true;
                    options.listener.onDone();
                    break;
                }
            }
        }
    }

    /**
     * @param currentTestChunk
     * @param chunkNumber       当前上传块
     * @param numberOfChunks    总块数
     * @param filename          文件名称
     * @param original_filename 源文件名称
     * @param identifier        文件
     * @param listener          监听
     * @param fileType          文件类型
     * @return
     */
    private int testChunkExists(int currentTestChunk, int chunkNumber, int numberOfChunks, String filename,
                                String original_filename, String identifier, UploadListener listener, String fileType) {
        String cfile;
        while (true) {
            cfile = getChunkFilename(currentTestChunk, identifier);
            if (Files.exists(Paths.get(cfile))) {
                currentTestChunk++;
                if (currentTestChunk >= chunkNumber) {
                    if (chunkNumber == numberOfChunks) {
                        try {
                            log.info("文件:{}上传成功,开始合并文件", original_filename);
                            UploadOptions options = new UploadOptions();
                            File f = new File(this.diskFolder,
                                    identifier + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(original_filename));
                            //TODO: 保存路径至数据库
                            //md5 = DigestUtils.md5DigestAsHex(new FileInputStream(f));
                            //System.out.println(md5);
                            options.listener = new UploadDoneListener() {
                                @Override
                                public void onError(Exception err) {
                                    listener.callback("invalid_uploader_request", f.getAbsolutePath(), original_filename, identifier, fileType);
                                    clean(identifier, null);
                                }

                                @Override
                                public void onDone() {
                                    listener.callback("done", f.getAbsolutePath(), original_filename, identifier, fileType);
                                    clean(identifier, null);
                                }
                            };
                            this.write(identifier, f, options);
                            log.info("文件合并成功,路径:{}", this.diskFolder +
                                    identifier + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(original_filename));

                            break;
                        } catch (IOException e) {
                            log.error("找不到文件:{}", e);
                            listener.callback("invalid_uploader_request", filename, original_filename, identifier, fileType);
                        }
                    } else {
                        listener.callback("partly_done", filename, original_filename, identifier, fileType);
                    }
                }


            } else {
                listener.callback("partly_done", filename, original_filename, identifier, fileType);
                break;
            }


        }

        return currentTestChunk;
    }

    private void clean(String identifier, UploadOptions options) {
        if (options == null) {
            options = new UploadOptions();
        }
        pipeChunkRm(1, identifier, options);
    }

    private void pipeChunkRm(int number, String identifier, UploadOptions options) {
        while (true) {
            String chunkFilename = getChunkFilename(number, identifier);
            if (Files.exists(Paths.get(chunkFilename))) {
                try {
                    Files.delete(Paths.get(chunkFilename));
                } catch (IOException e) {
                    if (options.listener != null) {
                        options.listener.onError(e);
                    }
                }
                number++;
            } else {
                if (options.listener != null) {
                    options.listener.onDone();
                }
                break;
            }
        }

    }

    public interface UploadListener {
        void callback(String status, String filename, String original_filename, String identifier, String fileType);
    }

    public interface UploadDoneListener {
        void onDone();

        void onError(Exception err);
    }

    public static class UploadOptions {
        Boolean end;
        UploadDoneListener listener;
    }
}