package com.moge10086.website.api.controller;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.dto.EmailCode;
import com.moge10086.website.service.EmailCodeRedisService;
import com.moge10086.website.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import java.util.concurrent.TimeUnit;


/**
 * @author sq
 */
@Tag(name = "邮箱请求相关", description = "邮箱请求相关")
@RestController
@RequestMapping("email")
@Validated
public class EmailController {
    @Resource
    UserAccountService userAccountService;
    @Resource
    EmailCodeRedisService emailCodeRedisService;


    @Resource
    JavaMailSender mailSender;

    /**
     * 验证码主要是验证邮箱是否属于用户，无需根据业务分多个验证码;
     * 一个邮箱同时只能存在一个验证码;
     * 可能出现的恶意使用：反复请求、暴力破解、恶意骚扰;
     * 设置请求间隔时间、错误上限次数、过期时间
     *
     * @param userEmail
     * @return
     */
    @Operation(summary = "发送邮箱验证码", description = "发送邮箱验证码到指定邮箱")
    @GetMapping(value = "/sendCodeByEmail", consumes = {MediaType.ALL_VALUE})
    public JsonResult<String> sendCodeByEmail(
            @Parameter(description = "用户邮箱", required = true)
            @RequestParam @Email String userEmail) {
        //getExpire不存在则返回-2,验证请求间隔时间
        if (emailCodeRedisService.getExpire(userEmail) >= EmailCode.CAPTCHA_VALID_TIME - EmailCode.SEND_EMAIL_INTERVAL) {
            return JsonResult.errorMsg(StatusCode.REQUEST_FREQUENTLY, "验证码频繁请求");
        }
        //生成邮箱验证码信息、存储到redis
        EmailCode emailCode = new EmailCode(userEmail);
        emailCodeRedisService.set(userEmail, emailCode, EmailCode.CAPTCHA_VALID_TIME, TimeUnit.SECONDS);
        //发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("moge10086-注册验证码-TEST");
        message.setText("注册验证码(TEST)是：" + emailCode.getCode() + "\n5分钟有效");
        message.setTo(userEmail);
        message.setFrom("moge10086@moge10086.top");
        //发送邮件
        mailSender.send(message);
        return JsonResult.ok("验证码发送成功");
    }
}
