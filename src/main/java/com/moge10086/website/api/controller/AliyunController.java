package com.moge10086.website.api.controller;

import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.moge10086.website.common.AliyunUtils;
import com.moge10086.website.common.utils.JsonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 22872
 */
@Tag(name = "阿里云操作相关", description = "阿里云操作相关")
@RestController
@RequestMapping("aliyun")
@Validated
public class AliyunController {
    @Operation(summary = "获取阿里云OSS上传STS",description="返回阿里云OSS上传STS")
    @PostMapping(value = "/getAliyunUploadToken",consumes = {MediaType.ALL_VALUE})
    public JsonResult<AssumeRoleResponse.Credentials> getAliyunUploadToken(
            @Parameter(description = "Authorization")
            @RequestHeader("Authorization") String token){
        return JsonResult.ok(AliyunUtils.getUserUploadImageToken().getCredentials());
    }
}
