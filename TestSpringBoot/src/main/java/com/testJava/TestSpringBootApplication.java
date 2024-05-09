package com.testJava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//启动类是启动springBoot,并扫描本级路径和子级路径下的spring注解
@SpringBootApplication
@MapperScan("com.testJava.mapper")
public class TestSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringBootApplication.class,args);
    }
}
