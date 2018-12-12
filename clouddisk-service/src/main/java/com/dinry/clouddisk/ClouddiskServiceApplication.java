package com.dinry.clouddisk;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 82712
 */
@EnableSwagger2Doc
@SpringBootApplication
public class ClouddiskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouddiskServiceApplication.class, args);
    }
}
