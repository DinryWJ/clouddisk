package com.dinry.clouddisk.api;

import com.dinry.clouddisk.model.TFile;
import com.dinry.clouddisk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/12 14:14
 * @Description:
 */
@Slf4j
@RestController
public class UploadApi {
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
    public UploadApi(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = "/upload")
    @RequiresAuthentication
    public ResponseEntity<ApiResponse> uploadPost(String md5, int chunkNumber, long chunkSize, long totalSize, String identifier, String filename, MultipartFile file) {
        Map<String, String> resultMap = new HashMap<>(2);
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
                Map<String, String> tempMap = testChunkExists(currentTestChunk, chunkNumber, numberOfChunks,
                        chunkFilename, originalFilename, identifier, "file", totalSize, md5);
                if (500 == Integer.parseInt(tempMap.get("code"))) {
                    resultMap.put("success", "false");
                    resultMap.put("result", tempMap.get("result"));
                    return ApiResponse.validResponse(String.valueOf(resultMap));
                }
                if (200 == Integer.parseInt(tempMap.get("code"))) {
                    resultMap.put("success", "true");
                    resultMap.put("fileId", tempMap.get("result"));
                } else {
                    resultMap.put("success", "false");
                    resultMap.put("result", "party_done");
                }
                return ApiResponse.successResponse(resultMap);
            } else {
                return ApiResponse.validResponse(validation);
            }
        } else {
            return ApiResponse.validResponse("invalid_uploader_request");
        }
    }

    @GetMapping(path = "/upload")
    @RequiresAuthentication
    public ResponseEntity<ApiResponse> uploadGet(String md5, int chunkNumber, int totalChunks, long chunkSize, long totalSize, String identifier, String filename) {
        Map<String, String> resultMap = new HashMap<>(2);
        //TODO:若文件MD5相同，在最后一块文件块时转储。
        if (md5 != null && fileService.isExist(md5)) {
            log.info("转储文件{}", filename);
            int[] arr = new int[totalChunks];
            for (int i = 0; i < totalChunks; i++) {
                arr[i] = i + 1;
            }
            int fileId = fileService.getFileIdByMd5(md5);
            resultMap.put("success", "true");
            resultMap.put("chunks", Arrays.toString(arr));
            resultMap.put("fileId", fileId + "");
            return ApiResponse.successResponse(resultMap);
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < totalChunks; i++) {
            String chunkFilename = getChunkFilename(i, identifier);
            if (Files.exists(Paths.get(chunkFilename))) {
                resultList.add(i);
            }
        }
        resultMap.put("success", "false");
        resultMap.put("chunks", String.valueOf(resultList));
        return ApiResponse.successResponse(resultMap);
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
    private Map<String, String> testChunkExists(int currentTestChunk, int chunkNumber, int numberOfChunks, String filename,
                                                String originalFilename, String identifier, String fileType, long totalSize, String md5) {
        String cfile;
        Map<String, String> resultMap = new HashMap<>(2);
        while (true) {
            cfile = getChunkFilename(currentTestChunk, identifier);
            if (!Files.exists(Paths.get(cfile))) {
                resultMap.put("code", 201 + "");
                resultMap.put("result", "file not exist");
                return resultMap;
            }
            currentTestChunk++;
            if (currentTestChunk >= chunkNumber) {
                if (chunkNumber == numberOfChunks) {
                    try {
                        log.info("文件:{}上传成功,开始合并文件", originalFilename);
                        String path = this.diskFolder + LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + "-" + identifier + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(originalFilename);
                        int eff = write(identifier, path);
                        if (eff == 1) {
                            TFile tFile = new TFile();
                            tFile.setSize(totalSize + "");
                            tFile.setPath(path);
                            tFile.setMd5(md5);
                            tFile.setName(identifier);
                            fileService.saveFile(tFile);

                            resultMap.put("code", 200 + "");
                            resultMap.put("result", tFile.getId() + "");
                        }
                        log.info("文件合并成功,路径:{}", path);
                        return resultMap;
                    } catch (IOException e) {
                        log.error("找不到文件:{}", e);
                        resultMap.put("code", 500 + "");
                        resultMap.put("result", "can not find file");
                        return resultMap;
                    }
                } else {
                    resultMap.put("code", 201 + "");
                    resultMap.put("result", "upload not complete");
                    return resultMap;
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