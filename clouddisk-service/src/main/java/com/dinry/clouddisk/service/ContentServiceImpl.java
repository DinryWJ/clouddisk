package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dao.ContentMapper;
import com.dinry.clouddisk.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/26 10:27
 * @Description:
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
    private final ContentMapper contentMapper;

    @Autowired
    public ContentServiceImpl(ContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }

    @Override
    public List<Content> getContent(int parentId) {
        Example example = new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        return contentMapper.selectByExample(example);
    }
}
