package com.moge10086.website.api.controller;

import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Operation(summary = "获取用户展示信息", description = "获取用户展示信息")
    @GetMapping(value = "/getUserInfo", consumes = {MediaType.ALL_VALUE})
    public JsonResult<BaseUserVO> getUserInfo(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @Parameter(description = "用户ID", required = true)
            @RequestParam Long userId){
        return JsonResult.ok(userInfoService.getBaseUserVO(userId));
    }

}
