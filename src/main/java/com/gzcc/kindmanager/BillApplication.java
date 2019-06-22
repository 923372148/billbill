package com.gzcc.kindmanager;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@EnableDubbo
@SpringBootApplication
@ImportResource(value = {"classpath:consumer.xml"})
@EnableAutoConfiguration
public class BillApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillApplication.class, args);
    }

}

