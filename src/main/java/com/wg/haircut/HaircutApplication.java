package com.wg.haircut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wg.haircut.dao")
public class HaircutApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaircutApplication.class, args);
    }
}
