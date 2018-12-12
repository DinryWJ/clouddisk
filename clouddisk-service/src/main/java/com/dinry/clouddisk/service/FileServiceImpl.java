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
        tFile.setRes(1);
        tFile.setCreateTime(new Date());
        tFile.setUpdateTime(new Date());
        return tFileMapper.insert(tFile);
    }

    @Override
    public int getFileIdByMd5(String md5) {
        TFile tFile = new TFile();
        tFile.setMd5(md5);
        tFile.setIsDelete(false);
        return tFileMapper.selectOne(tFile).getId();
    }

    @Override
    public TFile getById(Integer fileId) {
        TFile tFile = new TFile();
        tFile.setId(fileId);
        tFile.setIsDelete(false);
        return tFileMapper.selectOne(tFile);
    }

    @Override
    public int decreaseFileRes(Integer id, Integer res) {
        return tFileMapper.updateFileRes(id, res, res - 1, new Date());
    }

    @Override
    public int deleteFile(Integer id, Integer res) {
        return tFileMapper.deleteFile(id, res, res - 1, new Date());
    }

    @Override
    public int addFileRes(int fileId) {
        TFile tFile = getById(fileId);
        int res = tFile.getRes();
        return tFileMapper.updateFileRes(fileId, res, res + 1, new Date());
    }

    @Override
    public TFile getEmptyFileByName(String filename) {
        Example example = new Example(TFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", filename);
        criteria.andEqualTo("size", "0");
        criteria.andEqualTo("isDelete", false);
        return tFileMapper.selectOneByExample(example);
    }


}