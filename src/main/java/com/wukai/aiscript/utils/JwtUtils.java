package com.wukai.aiscript.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class JwtUtils {

    private static String SECRET_KEY;
    private static long EXPIRE;

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
     */
    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 【新增】解析 Token 获取 Claims 载荷
     */
    public static Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // 解析失败（过期或篡改）
            return null;
        }
    }

    /**
     * 【新增】从 Token 中获取用户 ID
     */
    public static Long getUserId(String token) {
        Claims claims = getClaimsByToken(token);
        if (claims != null) {
            return Long.parseLong(claims.get("userId").toString());
        }
        return null;
    }

    /**
     * 【新增】校验 Token 是否有效
     */
    public static boolean validateToken(String token) {
        return getClaimsByToken(token) != null;
    }
}