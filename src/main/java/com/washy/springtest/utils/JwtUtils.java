package com.washy.springtest.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author washy
 */
public class JwtUtils {

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
    private static final String SECRET_KEY_STRING = "washy-secret-json-web-token-authority";

    /**
     * 生成token
     */
    public static String generateToken(Long userId, String nickName) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

        Map<String, Object> claims = new HashMap<>(2);
        claims.put("userId", userId);
        claims.put("nickName", nickName);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 校验token
     */
    public static boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解析token并获取所有声明
     */
    public static Claims getAllClaimsFromToken(String token) {
        byte[] keyBytes = SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从Token中获取用户名
     */
    public static String getNickName(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("nickName", String.class);
    }

    /**
     * 从Token中获取用户ID
     */
    public static Long getUserId(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("userId", Long.class);
    }

    /**
     * 检查Token是否已过期
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getAllClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}
