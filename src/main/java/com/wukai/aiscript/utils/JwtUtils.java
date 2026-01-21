package com.wukai.aiscript.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtils {
    // 务必与 application.yml 中的 jwt.secret 保持一致，或者读取配置
    // 这里为了简单演示直接写死，生产环境建议 @Value("${jwt.secret}")
    private static final String SECRET_KEY = "wukai_ai_drama_saas_secret_key_2024";
    private static final long EXPIRE = 604800000; // 7天

    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}