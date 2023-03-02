package com.moge10086.website.api.controller;

import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.bo.UserInfoModifyBO;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 22872
 */
@Tag(name = "用户操作相关", description = "用户操作相关")
@RestController
@RequestMapping("userManage")
@Validated
public class UserManageController {
    @Resource
    UserInfoService userInfoService;

    /**
     * 更新用户基本信息,返回用户基本信息
     * @param token
     * @param userInfoModifyBO
     * @return
     */
    @Operation(summary = "更新用户基本信息", description = "更新用户基本信息,返回基本信息")
    @PostMapping(value = "/updateUserInfo",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<BaseUserVO> updateUserInfo(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid UserInfoModifyBO userInfoModifyBO){
        Long userId= JwtUtils.getUserIdFromToken(token);
        userInfoModifyBO.setUserId(userId);
        userInfoService.updateUserInfo(userInfoModifyBO);
        return JsonResult.ok(userInfoService.getBaseUserVO(userId));
    }
    /**
     * 返回用户基本信息
     * @param token
     * @return
     */
    @Operation(summary = "获得用户基本信息", description = "更新用户基本信息,返回基本信息")
    @GetMapping(value = "/getUserInfo")
    public JsonResult<BaseUserVO> updateUserInfo(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token){
        Long userId= JwtUtils.getUserIdFromToken(token);
        return JsonResult.ok(userInfoService.getBaseUserVO(userId));
    }
}
