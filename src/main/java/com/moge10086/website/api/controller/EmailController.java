package com.moge10086.website.api.controller;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;


/**
 * @author sq
 */
@Tag(name="邮箱请求相关",description = "邮箱请求相关")
@RestController
@RequestMapping("email")
@Validated
public class EmailController {
    @Resource
    UserAccountService userAccountService;
    @Operation(summary = "发送邮箱验证码", description = "发送邮箱验证码到指定邮箱")
    @GetMapping(value = "/sendCodeByEmail",consumes = {MediaType.ALL_VALUE} )
    public JsonResult<String> sendCodeByEmail(
            @Parameter(description = "用户邮箱", required = true)
            @RequestParam @Email String userEmail){
//        验证码主要是验证邮箱是否属于用户，无需根据业务分多个验证码；
        //可能出现的恶意使用：反复请求、暴力破解、恶意骚扰
        //一个用户请求3次没响应则当天禁止验证码请求
        //一个验证码最多错误3次

        return JsonResult.ok("验证码发送成功");
    }
}
