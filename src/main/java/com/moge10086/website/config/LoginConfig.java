package com.moge10086.website.config;

import com.moge10086.website.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 邵权
 * @describe 登录验证拦截器配置路径
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册Interceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //要拦截的路径
        registration.addPathPatterns("/postManage/**")
                    .addPathPatterns("/userManage/**")
                    .addPathPatterns("/aliyun/**")
                    .addPathPatterns("/postComment/publishComment");
    }
}
