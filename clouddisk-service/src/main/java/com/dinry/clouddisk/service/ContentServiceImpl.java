package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dao.ContentMapper;
import com.dinry.clouddisk.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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

    @Override
    public int saveFolderByRelativePath(String relativePath, int directoryId, int userId) {
        int parentId = directoryId;
        boolean flag = true;
        String[] folders = relativePath.split("/");
        for (int i = 0; i < folders.length - 1; i++) {
            Content content = new Content();
            content.setName(folders[i]);
            content.setUserId(userId);
            content.setParentId(parentId);
            if (flag && contentMapper.selectCount(content) < 1) {
                flag = false;
                content.setCreateTime(new Date());
                content.setUpdateTime(new Date());
                int eff = contentMapper.insert(content);
                parentId = content.getId();
            } else {
                parentId = contentMapper.selectOne(content).getId();
            }
        }
        return parentId;
    }
}
