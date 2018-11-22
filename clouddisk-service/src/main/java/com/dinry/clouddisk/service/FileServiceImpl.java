package com.dinry.clouddisk.service;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/12 14:27
 * @Description:
 */

import com.dinry.clouddisk.dao.TFileMapper;
import com.dinry.clouddisk.model.TFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private final TFileMapper tFileMapper;

    @Autowired
    public FileServiceImpl(TFileMapper tFileMapper) {
        this.tFileMapper = tFileMapper;
    }

    @Override
    public boolean isExist(String md5) {
        Example example = new Example(TFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("md5", md5);
        criteria.andEqualTo("isDelete", false);
        return tFileMapper.selectCountByExample(example) > 0;
    }

    @Override
    public int saveFile(TFile tFile) {
        tFile.setIsDelete(false);
        tFile.setCreateTime(new Date());
        tFile.setUpdateTime(new Date());
        return tFileMapper.insert(tFile);
    }

    @Override
    public int transferFileByMd5(String md5, int userId) {
        return 0;
    }

    @Override
    public int getFileIdByMd5(String md5) {
        TFile tFile = new TFile();
        tFile.setMd5(md5);
        return tFileMapper.selectOne(tFile).getId();
    }


}