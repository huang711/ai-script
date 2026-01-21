package com.wukai.aiscript;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wukai.aiscript.mapper") //扫描 mapper 包路径
public class AiScriptApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiScriptApplication.class, args);
    }

}