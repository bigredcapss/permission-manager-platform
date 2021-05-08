package com.we.pmp.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author we
 * @date 2021-05-08 11:25
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.we.pmp.model.mapper")
public class StartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);
    }
}
