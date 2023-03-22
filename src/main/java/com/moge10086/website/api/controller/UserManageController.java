package com.moge10086.website.api.controller;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.bo.UserInfoModifyBO;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.service.UserFollowService;
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
    @Resource
    UserFollowService userFollowService;

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
            @Parameter(description = "userInfoModifyBO", required = true)
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
    @Operation(summary = "关注/取消关注用户", description = "关注用户，返回是否关注")
    @GetMapping(value = "/followUser")
    public JsonResult<Boolean> followUser(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "被关注用户id", required = true)
            @RequestParam Long followedUserId){
        Long userId= JwtUtils.getUserIdFromToken(token);
        //自己不能关注自己
        if(userId.equals(followedUserId)){
            return JsonResult.errorMsg(StatusCode.NO_FIND_USER,"自己不能关注自己");
        }
        //验证被关注用户是否存在
        if (userInfoService.getBaseUserVO(followedUserId)==null){
            return JsonResult.errorMsg(StatusCode.NO_FIND_USER,"该用户不存在");
        }
        return JsonResult.ok(userFollowService.followUser(userId,followedUserId));
    }
}
