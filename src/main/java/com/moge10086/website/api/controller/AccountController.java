package com.moge10086.website.api.controller;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.common.utils.PasswordUtils;
import com.moge10086.website.domain.dto.EmailCode;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.domain.vo.user.UserLoginVO;
import com.moge10086.website.service.EmailCodeRedisService;
import com.moge10086.website.service.UserAccountService;
import com.moge10086.website.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
    EmailCodeRedisService emailCodeRedisService;
    @Resource
    UserInfoService userInfoService;

    /**
     * @description 验证该邮箱是否被注册,true为已被注册
     * @param userEmail 邮箱
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
     * @param userEmail
     * @param password
     * @param code
     * @return JsonResult<UserLoginVO>
     */
    @Operation(summary = "注册、重置账号", description = "通过邮箱,密码,验证码进行注册,如果成功则返回用户信息")
    @PostMapping(value = "/registerOrResetByEmail",consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public JsonResult<UserLoginVO> registerByEmail(
            @Parameter(description = "用户邮箱:长度需要在5和40之间", required = true)
            @RequestParam @Size(min=5,max=40,message = "用户邮箱:长度需要在5和40之间") String userEmail,
            @Parameter(description = "用户密码:32位加密后的MD5字符串", required = true)
            @RequestParam @Size(min=32,max=32,message = "用户密码:32位加密后的MD5字符串") String password,
            @Parameter(description = "邮箱验证码", required = true)
            @RequestParam String code){
        //从redis中取出验证码
        EmailCode emailCode=emailCodeRedisService.get(userEmail);
        //验证验证码：不存在、错误次数过多
        if (emailCode==null||emailCode.getCount()>=EmailCode.CAPTCHA_ERROR_LIMIT){
            return JsonResult.errorMsg(StatusCode.INVALID_CAPTCHA,"验证码无效或过期,请重新获取");
        }
        //校验验证码，自动更新到redis
        if (!emailCode.compareWithCode(code)){
            return JsonResult.errorMsg(StatusCode.ERROR_CAPTCHA,"验证码错误");
        }
        //注册,二次加密密码
        Long userId=userAccountService.registerOrReset(userEmail, PasswordUtils.md5Password(password));
        //生成用户登录信息
        return JsonResult.ok(createUserLoginVO(userId));
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
            @RequestParam @Email(message = "非法的邮件地址") String userEmail,
            @Parameter(description = "初步加密后的密码", required = true)
            @RequestParam @Size(min=32,max=32) String password){
        Long userId = userAccountService.loginByEmailAndPwd(userEmail, PasswordUtils.md5Password(password));
        if (userId==null){
            return JsonResult.errorMsg(StatusCode.ERROR_LOGIN,"用户不存在或密码错误");
        }
        return JsonResult.ok(createUserLoginVO(userId));
    }
    /**
     * 生成用户登录信息
     * @param userId
     * @return UserLoginVO
     */
    private UserLoginVO createUserLoginVO(Long userId){
        //获取用户基本信息
        BaseUserVO baseUserVO = userInfoService.getBaseUserVO(userId);
        UserLoginVO userLoginVO =new UserLoginVO(baseUserVO);
        //生成token
        userLoginVO.setUserToken(JwtUtils.createJws(baseUserVO));
        return userLoginVO;
    }
}
