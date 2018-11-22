package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.config.MyMapper;
import com.dinry.clouddisk.model.FileContent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author YFZX-WJJ-1778
 */
public interface FileContentMapper extends MyMapper<FileContent> {
    @Select("select name from file_content where name like CONCAT(#{fileName},'%') and directory_id = #{directoryId}")
    List<String> selectFileContentNames(@Param("fileName") String fileName, @Param("directoryId") int directoryId);
}