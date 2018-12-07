package com.dinry.clouddisk.service;

import com.dinry.clouddisk.ClouddiskServiceApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: 吴佳杰
 * @Date: 2018/12/7 10:18
 */
public class FileServiceImplTest extends ClouddiskServiceApplicationTests {
    @Autowired
    private FileService fileService;
    @Test
    public void decreaseFileRes() {
        Assert.assertEquals(fileService.decreaseFileRes(94, 2), 1);
    }
}