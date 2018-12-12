package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.config.MyMapper;
import com.dinry.clouddisk.model.FileContent;
import com.dinry.clouddisk.model.TFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author YFZX-WJJ-1778
 */
public interface FileContentMapper extends MyMapper<FileContent> {
    /**
     * 重名检测
     *
     * @param fileName
     * @param directoryId
     * @return name list
     */
    @Select("select name from file_content where name like CONCAT(#{fileName},'%') and directory_id = #{directoryId}")
    List<String> selectFileContentNames(@Param("fileName") String fileName, @Param("directoryId") int directoryId);


    /**
     * 通过fileContentId获取file信息
     *
     * @param fileContentId
     * @return TFile
     */
    @Select("SELECT file.* FROM file,file_content WHERE file_content.id = #{fileContentId} AND file_content.file_id = file.id")
    TFile getFileByFileContentId(@Param("fileContentId") int fileContentId);
}