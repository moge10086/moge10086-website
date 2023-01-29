package com.moge10086.website.common.jwt;

import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.enums.Role;
import io.jsonwebtoken.*;
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
     * 从token中获得用户id,如果token无效则返回-1
     * @param token
     * @return userId
     */
    public static Long getUserIdFromToken(String token){
        if (token==null){
            return -1L;
        }
        try {
            Claims claims = JwtUtils.parseJwsToClaims(token);
            return Long.parseLong(claims.get("userId").toString());
        } catch (JwtException jwtException) {
            return -1L;
        }
    }

    /**
     * 从token中获得用户role,无效则返回游客
     * @param token
     * @return userId
     */
    public static Integer getRoleFromToken(String token){
        if (token==null){
            return -1;
        }
        try {
            Claims claims = JwtUtils.parseJwsToClaims(token);
            return Integer.parseInt(claims.get("role").toString());
        }catch (JwtException jwtException){
            return Role.TOURIST.type;
        }
    }
}
