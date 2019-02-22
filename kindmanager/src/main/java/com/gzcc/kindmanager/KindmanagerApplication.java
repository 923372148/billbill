package com.gzcc.kindmanager;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.gzcc.kindmanager.*")
@SpringBootApplication
//@EnableDubbo
public class KindmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KindmanagerApplication.class, args);
    }

}

