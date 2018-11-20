package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dao.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:05
 * @Description:
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {

    private final ContentMapper contentMapper;

    @Autowired
    public ContentServiceImpl(ContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }
}
