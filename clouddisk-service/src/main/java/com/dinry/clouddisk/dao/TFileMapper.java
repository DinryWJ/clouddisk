package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.config.MyMapper;
import com.dinry.clouddisk.model.TFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface TFileMapper extends MyMapper<TFile> {
    /**
     * 减少文件引用数
     *
     * @param id
     * @param res
     * @return
     */
    @Update("UPDATE file SET res = #{val},update_time = #{date} WHERE id = #{id} AND res = #{res}")
    int updateFileRes(@Param("id") Integer id, @Param("res") Integer res, @Param("val") Integer val, @Param("date") Date date);

    @Update("UPDATE file SET is_delete = 1,res = #{val},update_time = #{date} WHERE id = #{id} AND res = #{res}")
    int deleteFile(@Param("id") Integer id, @Param("res") Integer res, @Param("val") Integer val, @Param("date") Date date);
}