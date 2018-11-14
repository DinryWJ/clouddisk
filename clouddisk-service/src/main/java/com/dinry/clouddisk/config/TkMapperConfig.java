package com.dinry.clouddisk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/6 14:55
 * @Description:
 */
@Configuration
public class TkMapperConfig {

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.dinry.clouddisk.dao");
        Properties propertiesMapper = new Properties();
        //通用mapper位置，不要和其他mapper、dao放在同一个目录
        propertiesMapper.setProperty("mappers", "com.dinry.clouddisk.config.MyMapper");
        propertiesMapper.setProperty("notEmpty", "false");
        //主键UUID回写方法执行顺序,默认AFTER,可选值为(BEFORE|AFTER)
        propertiesMapper.setProperty("ORDER","BEFORE");
        mapperScannerConfigurer.setProperties(propertiesMapper);
        return mapperScannerConfigurer;
    }

}