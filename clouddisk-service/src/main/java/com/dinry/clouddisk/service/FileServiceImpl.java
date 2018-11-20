package com.dinry.clouddisk.service;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/12 14:27
 * @Description:
 */

import com.dinry.clouddisk.dao.FileMapper;
import com.dinry.clouddisk.model.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public boolean isExist(String md5) {
        Example example = new Example(File.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("md5", md5);
        criteria.andEqualTo("isDelete", false);
        return fileMapper.selectCountByExample(example) > 0;
    }

    @Override
    public int saveFile(String path, String name, String size, String md5) {
        File file = new File();
        file.setId(null);
        file.setName(name);
        file.setPath(path);
        file.setMd5(md5);
        file.setSize(size);
        file.setIsDelete(false);
        file.setCreateTime(new Date());
        file.setUpdateTime(new Date());
        return fileMapper.insert(file);
    }

    @Override
    public int transferFileByMd5(String md5, int userId) {
        return 0;
    }


}