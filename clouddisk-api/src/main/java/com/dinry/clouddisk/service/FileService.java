package com.dinry.clouddisk.service;

import com.dinry.clouddisk.model.TFile;

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
     * @param tFile
     * @return
     */
    int saveFile(TFile tFile);

    /**
     * 转储文件 by md5
     * @param md5
     * @param userId
     * @return
     */
    int transferFileByMd5(String md5, int userId);

    /**
     * 获取相同md5文件的id
     * @param md5
     * @return
     */
    int getFileIdByMd5(String md5);
}
