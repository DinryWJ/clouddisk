package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.config.MyMapper;
import com.dinry.clouddisk.dto.FileInfo;
import com.dinry.clouddisk.model.FileContent;
import com.dinry.clouddisk.model.TFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
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

    /**
     * 获取文件信息
     *
     * @param fileContentId
     * @param userId
     * @return
     */
    @Select("SELECT file.id,file_content.`name`,file.path FROM file,file_content WHERE file_content.id = #{fileContentId} AND file_content.user_id = #{userId} AND file_content.file_id = file.id")
    FileInfo getFileInfo(@Param("fileContentId") int fileContentId, @Param("userId") Integer userId);

    /**
     * 重命名文件
     *
     * @param fileId
     * @param name
     * @param userId
     * @param date
     * @return
     */
    @Update("UPDATE file_content SET `name` = #{name},`update_time`= #{date} WHERE user_id = #{userId} AND id = #{fileId}")
    int renameFile(@Param("fileId") int fileId, @Param("name") String name, @Param("userId") Integer userId, @Param("date") Date date);
}