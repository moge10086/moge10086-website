package com.moge10086.website.api.controller;

import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.bo.PostArticleBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 22872
 */
@Tag(name = "帖子操作相关", description = "帖子操作相关")
@RestController
@RequestMapping("postOpt")
@Validated
public class PostOptController {
    //保存文章-》草稿模式
    //编辑文章-》草稿模式
    //发布文章-》审核模式/展示模式
    //删除文章-》删除模式

    /**
     * @description 上传文章进行注册,如果成功则返回用户信息
     * @return JsonResult<Long>
     */
    @Operation(summary = "保存、编辑文章", description = "保存、编辑文章，如果有id则为编辑、无id则为保存，返回帖子id")
    @PostMapping(value = "/saveOrEditArticlePost",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Long> saveOrEditArticle(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "文章帖子内容", required = true)
            @Valid @RequestBody PostArticleBO postArticleBO){
        /*  参数校验，字段是否合法
            如果携带id的话：判断帖子id是否存在，是否属于该用户，状态是否合法（锁定，删除）
        */

        //执行保存、编辑功能
        //返回id
        return JsonResult.ok(1L);
    }
}
