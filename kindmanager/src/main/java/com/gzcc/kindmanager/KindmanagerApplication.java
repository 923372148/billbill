package com.gzcc.kindmanager;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//@EnableDubboConfig
//@ComponentScan(basePackages="com.gzcc.kindmanager.*")
@SpringBootApplication
@ImportResource(value = {"classpath:provider.xml"})
//@EnableDubbo
public class KindmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KindmanagerApplication.class, args);
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"http://10.20.160.198/wiki/display/dubbo/provider.xml"});
//        context.start();
    }

}

