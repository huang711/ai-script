package com.wukai.aiscript.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // 1. 加上这个注解，让 Spring 能够管理它
public class JwtUtils {

    // 静态变量，用于在静态方法中使用
    private static String SECRET_KEY;
    private static long EXPIRE;

    // 2. 使用非静态的 Setter 方法注入配置，并赋值给静态变量
    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey) {
        JwtUtils.SECRET_KEY = secretKey;
    }

    @Value("${jwt.expire}")
    public void setExpire(long expire) {
        JwtUtils.EXPIRE = expire;
    }

    /**
     * 生成 Token
     * 注意：这里依然保持 static，这样您之前的 AuthService 代码就不用改了
     */
    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) // 使用读取到的配置
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用读取到的配置
                .compact();
    }
}