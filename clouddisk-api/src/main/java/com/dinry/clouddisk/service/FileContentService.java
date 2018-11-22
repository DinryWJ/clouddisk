package com.dinry.clouddisk.service;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:04
 * @Description:
 */
public interface FileContentService {
    /**
     * 文件上传后，保存至用户目录表
     *
     * @param fileId
     * @param name
     * @param directoryId
     * @param fileType
     * @param userId
     * @return
     */
    int saveFileToContent(int fileId, String name, int directoryId, String fileType, Integer userId);

    /**
     * 检查文件重复并修改
     *
     * @param fileName
     * @param directoryId
     * @return
     */
    String detectFileNameDuplicate(String fileName, int directoryId);
}
