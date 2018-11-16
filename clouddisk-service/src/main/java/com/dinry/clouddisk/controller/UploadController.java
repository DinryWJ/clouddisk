package com.dinry.clouddisk.controller;

import com.dinry.clouddisk.api.ApiResponse;
import com.dinry.clouddisk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/12 14:14
 * @Description:
 */
@Slf4j
@RestController
public class UploadController {
    private final FileService fileService;
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
    public UploadController(FileService fileService) {
        this.fileService = fileService;
    }

    public static void main(String[] args) throws IOException {
        String md = DigestUtils.md5DigestAsHex(new FileInputStream(new File("C:\\下载\\LOL_V4.0.8.2_FULL.7z.004")));
        String md2 = DigestUtils.md5DigestAsHex(new FileInputStream(new File("E:\\disk\\home\\1992294400-LOL_V4082_FULL7z004.004")));
        System.out.println(md.equals(md2));
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<ApiResponse> uploadPost(String md5, int chunkNumber, long chunkSize, long totalSize, String identifier, String filename, MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            String originalFilename = file.getOriginalFilename();
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
                        chunkFilename, originalFilename, identifier, "file", totalSize, md5);
                if (500 == code) {
                    return ApiResponse.validResponse("invalid_uploader_request");
                }
                return ApiResponse.successResponse(200 == code ? "done" : "party_done");
            } else {
                return ApiResponse.validResponse(validation);
            }
        } else {
            return ApiResponse.validResponse("invalid_uploader_request");
        }
    }

    @GetMapping(path = "/upload")
    public ResponseEntity<ApiResponse> uploadGet(String md5, int chunkNumber, int totalChunks, long chunkSize, long totalSize, String identifier, String filename) {
        //TODO:若文件MD5相同，在最后一块文件块时转储。
        if (md5 != null && fileService.isExist(md5)) {
            if (chunkNumber == totalChunks) {
                log.info("转储文件{}", filename);
            }
            return ApiResponse.successResponse("found");
        }

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

    private int write(String identifier, String path) throws IOException {
        String chunkFilename;
        for (int number = 1; ; number++) {
            chunkFilename = getChunkFilename(number, identifier);
            if (!Files.exists(Paths.get(chunkFilename))) {
                break;
            }
            if (!Files.exists(Paths.get(path))) {
                Files.write(Paths.get(path), Files.readAllBytes(Paths.get(chunkFilename)), StandardOpenOption.CREATE);
            } else {
                Files.write(Paths.get(path), Files.readAllBytes(Paths.get(chunkFilename)), StandardOpenOption.APPEND);
            }
        }
        clean(identifier);
        return 1;
    }

    /**
     * @param currentTestChunk 循环变量
     * @param chunkNumber      当前上传块
     * @param numberOfChunks   总块数
     * @param filename         文件名称
     * @param originalFilename 源文件名称
     * @param identifier       文件
     * @param fileType         文件类型
     * @return 返回值200: done 201:partly_done 500:something woring
     */
    private int testChunkExists(int currentTestChunk, int chunkNumber, int numberOfChunks, String filename,
                                String originalFilename, String identifier, String fileType, long totalSize, String md5) {
        String cfile;
        while (true) {
            cfile = getChunkFilename(currentTestChunk, identifier);
            if (!Files.exists(Paths.get(cfile))) {
                return 201;
            }
            currentTestChunk++;
            if (currentTestChunk >= chunkNumber) {
                if (chunkNumber == numberOfChunks) {
                    try {
                        log.info("文件:{}上传成功,开始合并文件", originalFilename);
                        String path = this.diskFolder + LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + "-" + identifier + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(originalFilename);
                        int eff = write(identifier, path);
                        if (eff == 1) {
                            fileService.saveFile(path, identifier, totalSize + "", md5);
                        }
                        log.info("文件合并成功,路径:{}", path);
                        return 200;
                    } catch (IOException e) {
                        log.error("找不到文件:{}", e);
                        return 500;
                    }
                } else {
                    return 201;
                }
            }
        }
    }

    private void clean(String identifier) {
        pipeChunkRm(identifier);
    }

    private void pipeChunkRm(String identifier) {
        int number = 1;
        while (true) {
            String chunkFilename = getChunkFilename(number, identifier);
            if (!Files.exists(Paths.get(chunkFilename))) {
                break;
            }
            try {
                Files.delete(Paths.get(chunkFilename));
            } catch (IOException e) {
                log.error("文件删除错误:", e);
            }
            number++;
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
}