package com.dinry.clouddisk.service;

import com.dinry.clouddisk.model.Content;

import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/26 10:26
 * @Description:
 */
public interface ContentService {
    /**
     * 获取目录
     *
     * @param parentId
     * @return
     */
    List<Content> getContent(int parentId);
}