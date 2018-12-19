package com.dinry.clouddisk.service;

import com.dinry.clouddisk.dao.FileContentMapper;
import com.dinry.clouddisk.dto.FileInfo;
import com.dinry.clouddisk.model.FileContent;
import com.dinry.clouddisk.model.TFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        int lastIndexOfDot = fileName.lastIndexOf(".");
        String extension;
        String name;
        if (lastIndexOfDot == -1){
            extension = "";
            name = fileName;
        }else {
            extension = fileName.substring(lastIndexOfDot);
            name = fileName.substring(0, lastIndexOfDot);
        }
        StringBuilder sb = new StringBuilder(name);
        List<String> names = fileContentMapper.selectFileContentNames(name,directoryId);
        if (names.size()>0){
            int i = 1;
            while(true){
                if (names.contains(sb.append(extension).toString())){
                    if (lastIndexOfDot != -1){
                        sb.delete(sb.lastIndexOf("."),sb.length());
                    }
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

    @Override
    public List<FileContent> getFilesByFolderId(int folderId, int userId) {
        Example example = new Example(FileContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("directoryId", folderId);
        criteria.andEqualTo("userId", userId);
        example.setOrderByClause("update_time asc");
        return fileContentMapper.selectByExample(example);
    }

    @Override
    public int deleteContentFile(int fileContentId, Integer userId) {
        Example example = new Example(FileContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", fileContentId);
        criteria.andEqualTo("userId", userId);
        return fileContentMapper.deleteByExample(example);
    }

    @Override
    public FileContent getFileContentById(int fileContentId, Integer userId) {
        FileContent fileContent =  fileContentMapper.selectByPrimaryKey(fileContentId);
        if (fileContent != null && Objects.equals(fileContent.getUserId(), userId)) {
            return fileContent;
        }
        return null;
    }

    @Override
    public TFile getFileByFileContentId(int fileContentId) {
        return fileContentMapper.getFileByFileContentId(fileContentId);
    }

    @Override
    public FileInfo getFileInfo(int fileContentId, Integer userId) {
        return fileContentMapper.getFileInfo(fileContentId, userId);
    }

    @Override
    public int renameFile(int fileId, String name, Integer userId) {
        return fileContentMapper.renameFile(fileId, name, userId, new Date());
    }

}
