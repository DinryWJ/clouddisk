package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.ClouddiskServiceApplicationTests;
import com.dinry.clouddisk.dto.FileInfo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author: 吴佳杰
 * @Date: 2018/12/14 15:00
 * @Description:
 */
public class FileContentMapperTest extends ClouddiskServiceApplicationTests {

    @Autowired
    private FileContentMapper fileContentMapper;

    @Test
    @Ignore
    public void getFileInfo() {
        FileInfo fileInfo = fileContentMapper.getFileInfo(364, 2);
        System.out.println(fileInfo);
    }

    @Test
    public void renameFile() {
//        Assert.assertEquals(fileContentMapper.renameFile(263, "LICENSE", 2, new Date()), 1);
    }
}