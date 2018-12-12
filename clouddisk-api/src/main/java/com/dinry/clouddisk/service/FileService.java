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
     * 获取相同md5文件的id
     *
     * @param md5
     * @return
     */
    int getFileIdByMd5(String md5);

    /**
     * 根据id获取文件类
     *
     * @param fileId
     * @return
     */
    TFile getById(Integer fileId);

    /**
     * 减少文件引用数
     *
     * @param id
     * @param res
     * @return
     */
    int decreaseFileRes(Integer id, Integer res);

    /**
     * 删除文件
     *
     * @param id
     * @param res
     * @return
     */
    int deleteFile(Integer id, Integer res);

    /**
     * 文件引用数+1
     *
     * @param fileId
     * @return
     */
    int addFileRes(int fileId);

    /**
     * 空文件判断
     *
     * @param filename
     * @return
     */
    TFile getEmptyFileByName(String filename);
}
