package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.ClouddiskServiceApplicationTests;
import com.dinry.clouddisk.dto.FileInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}