package com.dinry.clouddisk.service;

import com.dinry.clouddisk.ClouddiskServiceApplicationTests;
import com.dinry.clouddisk.model.TFile;
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

    }

    @Test
    public void getEmptyFileByName() {
    }
}