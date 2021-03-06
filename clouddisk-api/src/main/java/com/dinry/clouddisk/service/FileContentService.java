package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dto.FileInfo;
import com.dinry.clouddisk.model.FileContent;
import com.dinry.clouddisk.model.TFile;

import java.util.List;

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
     * @param totalSize
     * @param directoryId
     * @param fileType
     * @param userId
     * @return
     */
    int saveFileToContent(int fileId, String name, String totalSize, int directoryId, String fileType, Integer userId);

    /**
     * 检查文件重复并修改
     *
     * @param fileName
     * @param directoryId
     * @return
     */
    String detectFileNameDuplicate(String fileName, int directoryId);

    /**
     * 获取目录下的所有文件目录
     *
     * @param folderId
     * @return
     */
    List<FileContent> getFilesByFolderId(int folderId, int userId);

    /**
     * 删除文件目录表下的某个文件
     *
     * @param fileContentId
     * @param userId
     */
    int deleteContentFile(int fileContentId, Integer userId);

    /**
     * 通过userid, id获取实体类
     *
     * @param fileContentId
     * @param userId
     * @return
     */
    FileContent getFileContentById(int fileContentId, Integer userId);

    /**
     * 通过fileContentId获取File信息
     *
     * @param fileContentId
     * @return
     */
    TFile getFileByFileContentId(int fileContentId);

    /**
     * 获取文件信息
     *
     * @param fileContentId id
     * @param userId
     * @return FileInfo
     */
    FileInfo getFileInfo(int fileContentId, Integer userId);

    /**
     * 重命名文件
     *
     * @param fileId id
     * @param name   name
     * @param userId userid
     * @return
     */
    int renameFile(int fileId, String name, Integer userId);
}
