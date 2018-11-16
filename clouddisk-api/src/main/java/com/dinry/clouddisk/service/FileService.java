package com.dinry.clouddisk.service;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/14 16:36
 * @Description:
 */
public interface FileService {
    /**
     * 根据 md5 判断文件是否存在
     *
     * @param md5
     * @return
     */
    boolean isExist(String md5);

    /**
     * 保存文件
     *
     * @param path
     * @param name
     * @param size
     * @param md5
     * @return
     */
    int saveFile(String path, String name, String size, String md5);

    /**
     * 转储文件 by md5
     * @param md5
     * @param userId
     * @return
     */
    int transferFileByMd5(String md5, int userId);
}
