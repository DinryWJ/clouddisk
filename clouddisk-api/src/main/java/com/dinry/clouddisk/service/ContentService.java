package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dto.FileInfo;
import com.dinry.clouddisk.model.Content;

import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/26 10:26
 * @Description:
 */
public interface ContentService {
    /**
     * 获取目录信息
     *
     * @param contentId
     * @return
     */
    Content getContentById(int contentId);

    /**
     * 获取文件夹下的所有目录
     *
     * @param parentId
     * @return
     */
    List<Content> getContent(int parentId, int userId);

    /**
     * 根据相对路径新建文件夹
     *
     * @param relativePath
     * @param directoryId
     * @return
     */
    int saveFolderByRelativePath(String relativePath, int directoryId, int userId);

    /**
     * 删除文件夹
     *
     * @param contentId
     * @param userId
     * @return
     */
    int deleteContent(int contentId, Integer userId);

    /**
     * 新建文件夹
     *
     * @param name
     * @param userId
     * @return
     */
    int saveContent(String name, int parentId, Integer userId);


    /**
     * 获取目录下的所有文件信息
     *
     * @param contentId
     * @return
     */
    List<FileInfo> getFileInfoByContentId(int contentId);

    /**
     * 重命名文件夹
     *
     * @param contentId id
     * @param name      新名字
     * @param userId
     * @return
     */
    int renameDirectory(int contentId, String name, Integer userId);
}
