package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dao.FileContentMapper;
import com.dinry.clouddisk.model.FileContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/20 10:05
 * @Description:
 */
@Service("contentService")
public class FileContentServiceImpl implements FileContentService {

    private final FileContentMapper fileContentMapper;

    @Autowired
    public FileContentServiceImpl(FileContentMapper fileContentMapper) {
        this.fileContentMapper = fileContentMapper;
    }

    @Override
    public int saveFileToContent(int fileId, String name, int directoryId, String fileType, Integer userId) {
        FileContent fileContent = new FileContent();
        fileContent.setFileId(fileId);
        fileContent.setName(name);
        fileContent.setDirectoryId(directoryId);
        fileContent.setFileType(fileType);
        fileContent.setUserId(userId);
        fileContent.setCreateTime(new Date());
        fileContent.setUpdateTime(new Date());
        return fileContentMapper.insert(fileContent);
    }

    @Override
    public String detectFileNameDuplicate(String fileName, int directoryId) {
        List<String> names = fileContentMapper.selectFileContentNames(fileName,directoryId);
        //TODO;fff
//        if (names.size()>0){
//            while(true){
//                int i = 0;
//                if (names.contains(fileName))
//            }
//        }
    }

}
