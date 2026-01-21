package com.wukai.aiscript;

import org.mybatis.spring.annotation.MapperScan; // 1. 务必添加这个包导入
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wukai.aiscript.mapper") // 2. 核心：加上这一行，扫描你的 mapper 包路径
public class AiScriptApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiScriptApplication.class, args);
    }

}