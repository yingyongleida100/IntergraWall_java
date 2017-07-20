package com.liazylee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liazylee.Mysql")
public class IntergraWallApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntergraWallApplication.class, args);
    }

}
