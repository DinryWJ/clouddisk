package com.dinry.clouddisk.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;

//https://github.com/simple-uploader/Uploader/blob/develop/samples/Node.js/uploader-node.js
public class Uploader {
    /**
     * 临时文件夹
     */
    @Value("${temporaryfolder}")
    private String temporaryFolder;
    /**
     * 最大文件大小
     */
    @Value("${maxfilesize}")
    private Integer maxFileSize;

//    public Uploader(String temporaryFolder, Integer maxFileSize) {
//        this.temporaryFolder = temporaryFolder;
//        this.maxFileSize = maxFileSize;
//        File file = new File(temporaryFolder);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//
//    }

    private String cleanIdentifier(String identifier) {
        return identifier.replaceAll("[^0-9A-Za-z_-]", "");
    }

    private String getChunkFilename(int chunkNumber, String identifier) {
        identifier = cleanIdentifier(identifier);
        return new File(temporaryFolder, "uploader-" + identifier + '.' + chunkNumber).getAbsolutePath();
    }

    private String validateRequest(int chunkNumber, int chunkSize, int totalSize, String identifier, String filename, Integer fileSize) {
        identifier = cleanIdentifier(identifier);

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
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
        }
        return def;
    }

    private String getParamString(HttpServletRequest req, String key, String def) {
        String value = req.getParameter(key);
        try {
            return value == null ? def : value;
        } catch (Exception e) {
        }
        return def;
    }

    public void get(HttpServletRequest req, UploadListener listener) {
        int chunkNumber = this.getParamInt(req, "chunkNumber", 0);
        int chunkSize = this.getParamInt(req, "chunkSize", 0);
        int totalSize = this.getParamInt(req, "totalSize", 0);
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

    public void post(HttpServletRequest req, UploadListener listener) throws IllegalStateException, IOException {
        int chunkNumber = this.getParamInt(req, "chunkNumber", 0);
        int chunkSize = this.getParamInt(req, "chunkSize", 0);
        int totalSize = this.getParamInt(req, "totalSize", 0);
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

                        File f = new File(chunkFilename);
                        if (!f.exists()) {
                            file.transferTo(f);
                        }

                        int currentTestChunk = 1;
                        int numberOfChunks = (int) Math.max(Math.floor(totalSize / (chunkSize * 1.0)), 1);

                        currentTestChunk = this.testChunkExists(currentTestChunk, chunkNumber, numberOfChunks,
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

    private void pipeChunk(int number, String identifier, UploadOptions options, OutputStream writableStream)
            throws IOException {
        String chunkFilename = getChunkFilename(number, identifier);
        if (new File(chunkFilename).exists()) {
            int maxlen = 1024;
            int len = 0;
            try (FileInputStream inputStream = new FileInputStream(new File(chunkFilename))) {
                byte[] buff = new byte[maxlen];
                while ((len = inputStream.read(buff, 0, maxlen)) > 0) {
                    writableStream.write(buff, 0, len);
                }
            }
            pipeChunk(number + 1, identifier, options, writableStream);
        } else {
            if (options.end)
                writableStream.close();
            if (options.listener != null)
                options.listener.onDone();
        }
    }

    private void write(String identifier, OutputStream writableStream, UploadOptions options) throws IOException {
        if (options == null) {
            options = new UploadOptions();
        }
        if (options.end == null) {
            options.end = true;
        }
        pipeChunk(1, identifier, options, writableStream);
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
        String cfile = getChunkFilename(currentTestChunk, identifier);
        if (new File(cfile).exists()) {
            currentTestChunk++;
            if (currentTestChunk >= chunkNumber) {
                if (chunkNumber == numberOfChunks) try {
                    System.out.println("done");
                    // 文件合并
                    UploadOptions options = new UploadOptions();
                    File f = new File(this.temporaryFolder,
                            identifier + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(original_filename));
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
                    this.write(identifier, new FileOutputStream(f), options);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.callback("invalid_uploader_request", filename, original_filename, identifier,
                            fileType);
                }
                else {
                    listener.callback("partly_done", filename, original_filename, identifier, fileType);
                }
            } else {
                return testChunkExists(currentTestChunk, chunkNumber, numberOfChunks, filename, original_filename,
                        identifier, listener, fileType);
            }
        } else {
            listener.callback("partly_done", filename, original_filename, identifier, fileType);
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
        String chunkFilename = getChunkFilename(number, identifier);
        File file = new File(chunkFilename);
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                if (options.listener != null) {
                    options.listener.onError(e);
                }
            }
            pipeChunkRm(number + 1, identifier, options);
        } else {
            if (options.listener != null) {
                options.listener.onDone();
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