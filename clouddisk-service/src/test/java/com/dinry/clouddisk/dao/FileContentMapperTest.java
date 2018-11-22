package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.ClouddiskServiceApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/21 16:49
 * @Description:
 */
public class FileContentMapperTest extends ClouddiskServiceApplicationTests {

    @Autowired
    private FileContentMapper fileContentMapper;

    @Test
    public void selectFileContentNames() {
        List<String> list = fileContentMapper.selectFileContentNames("Xcode_8.2.xip",0);
        Assert.assertEquals(2, list.size());
    }
}