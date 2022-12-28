package com.moge10086.website.api.controller;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.vo.user.UserLoginVO;
import com.moge10086.website.service.UserAccountService;
import com.moge10086.website.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sq
 */
@Tag(name="账号相关",description = "账号相关的接口")
@RestController
@RequestMapping("account")
@Validated
public class AccountController {
    @Resource
    UserAccountService userAccountService;
    @Resource
    UserInfoService userInfoService;

    /**
     * @description 验证该邮箱是否被注册,true为已被注册
     * @param userEmail
     * @return JsonResult<Boolean>
     */
    @Operation(summary = "验证邮箱是否被注册", description = "验证该邮箱是否被注册,存在则返回true")
    @PostMapping(value = "/verifyEmailRegistered",consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public JsonResult<Boolean> verifyEmailRegistered (
            @Parameter(description = "用户邮箱", required = true)
            @RequestParam @Email String userEmail) {
        return JsonResult.ok(userAccountService.emailIsExist(userEmail));
    }

    /**
     * @description 通过邮箱,密码,验证码进行注册,如果成功则返回用户信息
     * @param userName
     * @param userEmail
     * @param password
     * @param verCode
     * @return JsonResult<UserLoginVO>
     */
    @Operation(summary = "注册账号", description = "通过邮箱,密码,验证码进行注册,如果成功则返回用户信息")
    @PostMapping(value = "/registerByEmail",consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public JsonResult<UserLoginVO> registerByEmail(
            @Parameter(description = "用户昵称:长度需要在2和15之间", required = true)
            @RequestParam @Length(min=2,max=20) String userName,
            @Parameter(description = "用户邮箱:长度需要在5和30之间", required = true)
            @RequestParam @Length(min=5,max=40) String userEmail,
            @Parameter(description = "用户密码:32位加密后的MD5字符串", required = true)
            @RequestParam @Length(min=32,max=32) String password,
            @Parameter(description = "邮箱验证码", required = true)
            @RequestParam String verCode){
        /*
        1.验证
        验证字段合法性，验证邮箱验证码
        检测对应邮箱账户是否已存在
        2.处理
        作废验证码
        二次加密密码
        执行插入sql
        3.返回
        获取用户登录信息
        生成token

         */
        return JsonResult.ok(new UserLoginVO());
    }

    /**
     * @description 通过邮箱、密码进行登录，返回用户登录信息
     * @param userEmail
     * @param password
     * @return JsonResult<UserLoginVO>
     */
    @Operation(summary = "邮箱登录",description="通过邮箱和密码进行登录,如果成功则返回用户信息")
    @PostMapping(value = "/loginByEmail",consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public JsonResult<UserLoginVO> loginByEmail(
            @Parameter(description = "用户邮箱", required = true)
            @RequestParam @Email String userEmail,
            @Parameter(description = "初步加密后的密码", required = true)
            @RequestParam @Length(min=32,max=32) String password){
            return JsonResult.ok(new UserLoginVO());
    }

    /**
     * @description 重置密码
     * @param userEmail
     * @param password
     * @param verCode
     * @return JsonResult<Boolean>
     */
    @Operation(summary = "重置登录密码", description = "通过邮箱,密码,验证码重置密码")
    @PostMapping(value = "/resetLoginPassword",consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public JsonResult<Boolean> resetLoginPassword(
            @Parameter(description = "用户邮箱", required = true)
            @RequestParam @Email String userEmail,
            @Parameter(description = "用户密码", required = true)
            @RequestParam @Length(min=32,max=32) String password,
            @Parameter(description = "邮箱验证码", required = true)
            @RequestParam String verCode){

        return JsonResult.ok(Boolean.TRUE);
    }
}