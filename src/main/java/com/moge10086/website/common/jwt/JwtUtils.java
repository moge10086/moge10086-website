package com.moge10086.website.common.jwt;

import com.moge10086.website.domain.vo.user.BaseUserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author 邵权
 */
public class JwtUtils {
    /**
     * 自动生成的密钥
     */
    private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 创建BaseUserVO对应的jws
     * @param baseUserVO
     * @return String
     */
    public static String createJws(BaseUserVO baseUserVO){
        Date now = new Date();
        Calendar expiration=Calendar.getInstance();
        expiration.setTime(now);
        expiration.add(Calendar.DATE,3);
        JwtBuilder builder = Jwts.builder()
                // JWT_ID
                .setId(baseUserVO.getUserId().toString()+ UUID.randomUUID())
                // 接受者
                .setAudience(baseUserVO.getUserName())
                // 用户id
                .claim("userId",baseUserVO.getUserId())
                // 用户角色
                .claim("role",baseUserVO.getRole())
                // 主题
                .setSubject("JWT_LOGIN_TOKEN")
                // 签发者
                .setIssuer("moge10086")
                // 签发时间
                .setIssuedAt(now)
                // 生效时间
                .setNotBefore(now)
                // 过期时间
                .setExpiration(expiration.getTime())
                // 签名算法以及密匙
                .signWith(KEY);
        return builder.compact();
    }

    /**
     * 解析jws，返回Claims
     * @param jws
     * @return Claims
     */
    public static Claims parseJwsToClaims(String jws){
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(jws).getBody();
    }
    /**
     * 从token中获得用户id
     * @param token
     * @return userId
     */
    public static Long getUserIdFromToken(String token){
        Claims claims = JwtUtils.parseJwsToClaims(token);
        return (Long)claims.get("userId");
    }

    /**
     * 从token中获得用户role
     * @param token
     * @return userId
     */
    public static Integer getRoleFromToken(String token){
        Claims claims = JwtUtils.parseJwsToClaims(token);
        return (Integer)claims.get("role");
    }
}
