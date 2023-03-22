package com.moge10086.website.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.query.qo.user.QueryFanListBO;
import com.moge10086.website.domain.query.qo.user.QueryFollowedListBO;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.service.UserFollowService;
import com.moge10086.website.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author sq
 */
@Tag(name = "用户展示相关", description = "用户展示相关")
@RestController
@RequestMapping("userShow")
@Validated
public class UserShowController {
    @Resource
    UserInfoService userInfoService;
    @Resource
    UserFollowService userFollowService;
    @Operation(summary = "获取用户展示信息", description = "获取用户展示信息")
    @GetMapping(value = "/getUserInfo", consumes = {MediaType.ALL_VALUE})
    public JsonResult<BaseUserVO> getUserInfo(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @Parameter(description = "用户ID", required = true)
            @RequestParam Long userId){
        return JsonResult.ok(userInfoService.getBaseUserVO(userId));
    }
    @Operation(summary = "获得用户关注状态", description = "获得用户关注状态")
    @GetMapping(value = "/getFollowState", consumes = {MediaType.ALL_VALUE})
    public JsonResult<Boolean> getFollowState(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = true) String token,
            @Parameter(description = "被关注用户id", required = true)
            @RequestParam Long followedUserId){
        Long userId= JwtUtils.getUserIdFromToken(token);
        return JsonResult.ok(userFollowService.getFollowState(userId,followedUserId));
    }
    @Operation(summary = "获得用户粉丝列表", description = "获得用户粉丝列表")
    @PostMapping(value = "/getFanList",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Page<BaseUserVO>> getFanList(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @Parameter(description = "queryFanListBO", required = true)
            @RequestBody @Valid QueryFanListBO queryFanListBO){
        Long userId= JwtUtils.getUserIdFromToken(token);
        return JsonResult.ok(userFollowService.listFans(queryFanListBO));
    }
    @Operation(summary = "获得用户关注列表", description = "获得用户关注列表")
    @PostMapping(value = "/getFollowedList", consumes = {MediaType.ALL_VALUE})
    public JsonResult<Page<BaseUserVO>> getFollowedList(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @Parameter(description = "queryFollowedListBO", required = true)
            @RequestBody @Valid QueryFollowedListBO queryFollowedListBO){
        Long userId= JwtUtils.getUserIdFromToken(token);
        return JsonResult.ok(userFollowService.listFollowed(queryFollowedListBO));
    }

}
