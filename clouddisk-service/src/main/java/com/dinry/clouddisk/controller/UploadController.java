package com.dinry.clouddisk.controller;

import com.dinry.clouddisk.api.ApiResponse;
import com.dinry.clouddisk.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/12 14:14
 * @Description:
 */
@Slf4j
@RestController
public class UploadController {
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
    @Value("${maxfilesize}")
    private Long maxFileSize;

    @Autowired
    private UploadService uploadService;

    @RequestMapping(path = "/upload", method = {RequestMethod.POST})
    public ResponseEntity<ApiResponse> uploadPost(HttpServletRequest request, MultipartFile file) {
        int chunkNumber = this.getParamInt(request, "chunkNumber", 0);
        long chunkSize = this.getParamLong(request, "chunkSize", 0);
        long totalSize = this.getParamLong(request, "totalSize", 0);
        String identifier = this.getParamString(request, "identifier", "");
        String filename = this.getParamString(request, "filename", "");

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

                int code = testChunkExists(currentTestChunk, chunkNumber, numberOfChunks,
                        chunkFilename, original_filename, identifier, "file");

            } else {
                return ApiResponse.validResponse(validation);
            }
        } else {
            return ApiResponse.validResponse("invalid_uploader_request");
        }
    }

    @RequestMapping(path = "/upload", method = {RequestMethod.GET})
    public ResponseEntity<ApiResponse> uploadGet(HttpServletRequest request) {
        int chunkNumber = this.getParamInt(request, "chunkNumber", 0);
        long chunkSize = this.getParamLong(request, "chunkSize", 0);
        long totalSize = this.getParamLong(request, "totalSize", 0);
        String identifier = this.getParamString(request, "identifier", "");
        String filename = this.getParamString(request, "filename", "");
        if (validateRequest(chunkNumber, chunkSize, totalSize, identifier, filename, null).equals("valid")) {
            String chunkFilename = getChunkFilename(chunkNumber, identifier);
            if (Files.exists(Paths.get(chunkFilename))) {
                return ApiResponse.successResponse("found");
            } else {
                return ApiResponse.fileNotFoundResponse("not_found");
            }
        } else {
            return ApiResponse.fileNotFoundResponse("not_found");
        }
    }

    private void write(String identifier, String path) throws IOException {
        String chunkFilename;
        for (int number = 1; ; number++) {
            chunkFilename = getChunkFilename(number, identifier);
            if (!Files.exists(Paths.get(chunkFilename))) {
                break;
            }
            Files.write(Paths.get(path), Files.readAllBytes(Paths.get(chunkFilename)));
        }
    }

    /**
     * @param currentTestChunk
     * @param chunkNumber       当前上传块
     * @param numberOfChunks    总块数
     * @param filename          文件名称
     * @param original_filename 源文件名称
     * @param identifier        文件
     * @param fileType          文件类型
     * @return
     */
    private int testChunkExists(int currentTestChunk, int chunkNumber, int numberOfChunks, String filename,
                                String original_filename, String identifier, String fileType) {
        String cfile;
        while (true) {
            cfile = getChunkFilename(currentTestChunk, identifier);
            if (!Files.exists(Paths.get(cfile))) {
                return 200;
            }
            currentTestChunk++;
            if (currentTestChunk >= chunkNumber) {
                if (chunkNumber == numberOfChunks) {
                    try {
                        log.info("文件:{}上传成功,开始合并文件", original_filename);
                        String path = this.diskFolder + identifier + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(original_filename);
                        write(identifier, path);
                        log.info("文件合并成功,路径:{}", path);
                        return 200;
                    } catch (IOException e) {
                        log.error("找不到文件:{}", e);
                        return 500;
                    }
                } else {
                    return 200;
                }
            }
        }

    }

    private void clean(String identifier, Uploader.UploadOptions options) {
        if (options == null) {
            options = new Uploader.UploadOptions();
        }
        pipeChunkRm(1, identifier, options);
    }

    private void pipeChunkRm(int number, String identifier, Uploader.UploadOptions options) {
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

    private String getChunkFilename(int chunkNumber, String identifier) {
        return new File(temporaryFolder, "uploader-" + identifier + '.' + chunkNumber).getAbsolutePath();
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
}