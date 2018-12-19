package com.dinry.clouddisk.dao;

import com.dinry.clouddisk.ClouddiskServiceApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author: 吴佳杰
 * @Date: 2018/12/19 16:46
 * @Description:
 */
public class ContentMapperTest extends ClouddiskServiceApplicationTests {

    @Autowired
    private ContentMapper contentMapper;
    @Test
    public void renameDirectory() {
//        Assert.assertEquals(contentMapper.renameDirectory(63, "apache-maven-3.5.42", 2,new Date()), 1);
    }
}