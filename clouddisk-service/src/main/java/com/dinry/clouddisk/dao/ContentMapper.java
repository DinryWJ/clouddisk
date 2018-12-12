package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.config.MyMapper;
import com.dinry.clouddisk.dto.FileInfo;
import com.dinry.clouddisk.model.Content;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/21 13:58
 * @Description:
 */
public interface ContentMapper extends MyMapper<Content> {
    /**
     * 获取目录下的所有文件信息
     *
     * @param contentId
     * @return
     */
    @Select("SELECT file.id, file_content.`name`,file.path FROM file,file_content WHERE file_content.directory_id = #{contentId} AND file_content.file_id = file.id")
    List<FileInfo> getFileInfoByContentId(@Param("contentId") int contentId);
}
