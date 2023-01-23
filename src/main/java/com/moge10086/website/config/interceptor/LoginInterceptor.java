package com.moge10086.website.config.interceptor;

import com.moge10086.website.common.jwt.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 邵权
 * @describe 检查是否登录的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        //有异常会被全局统一处理
        JwtUtils.parseJwsToClaims(authorization);
        return true;
    }
}
