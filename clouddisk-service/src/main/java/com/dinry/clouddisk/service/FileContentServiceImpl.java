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
@Service("fileContentService")
public class FileContentServiceImpl implements FileContentService {

    private final FileContentMapper fileContentMapper;

    @Autowired
    public FileContentServiceImpl(FileContentMapper fileContentMapper) {
        this.fileContentMapper = fileContentMapper;
    }

    @Override
    public int saveFileToContent(int fileId, String name, String totalSize, int directoryId, String fileType, Integer userId) {
        FileContent fileContent = new FileContent();
        fileContent.setFileId(fileId);
        fileContent.setName(name);
        fileContent.setTotalSize(totalSize);
        fileContent.setDirectoryId(directoryId);
        fileContent.setFileType(fileType);
        fileContent.setUserId(userId);
        fileContent.setCreateTime(new Date());
        fileContent.setUpdateTime(new Date());
        return fileContentMapper.insert(fileContent);
    }

    @Override
    public String detectFileNameDuplicate(String fileName, int directoryId) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        StringBuilder sb = new StringBuilder(name);
        List<String> names = fileContentMapper.selectFileContentNames(name,directoryId);
        if (names.size()>0){
            int i = 1;
            while(true){
                if (names.contains(sb.append(extension).toString())){
                    sb.delete(sb.lastIndexOf("."),sb.length());
                    if (i==1){
                        sb.append("(").append(i).append(")");
                    }else {
                        sb.setCharAt(sb.length()-2, String.valueOf(i).charAt(0));
                    }
                }else {
                    break;
                }
                i++;
            }
        }else {
            sb.append(extension);
        }
        return sb.toString();
    }

}
