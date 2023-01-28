package com.moge10086.website.api.controller;

import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.vo.post.PostEntireVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 22872
 */
@Tag(name = "帖子展示相关", description = "帖子展示相关")
@RestController
@RequestMapping("postShow")
@Validated
public class PostShowController {
    @Operation(summary = "获取帖子展示信息列表", description = "获取帖子展示信息列表，用于卡片，列表等简约展示")
    @GetMapping(value = "/getPostShow", consumes = {MediaType.ALL_VALUE})
    public JsonResult<PostEntireVO> getPostShow(){
        /*
            验证帖子状态是否合法(是否存在，状态可以访问)
            验证用户是否有该帖子的浏览权限(作者，用户，游客)
            返回帖子详细信息
         */
        /*
            帖子ID、标题、封面、发布时间、类别、简介；浏览量、点赞、收藏、评论；作者ID、头像、名称
         */
        return JsonResult.ok(null);

    }

    //获取帖子详细信息
    @Operation(summary = "获取帖子详细信息", description = "获取帖子详细信息")
    @GetMapping(value = "/getEntirePost", consumes = {MediaType.ALL_VALUE})
    public JsonResult<PostEntireVO> getEntirePost(){
        /*
            帖子ID、标题、封面、发布时间、类别、状态、简介；浏览量、点赞、收藏、评论；作者ID、头像、名称、简介、数据
         */
        /*
            验证帖子状态是否合法(是否存在，状态可以访问)
            验证用户是否有该帖子的浏览权限(作者，用户，游客)
            返回帖子详细信息
         */
        return JsonResult.ok(null);

    }
    @Operation(summary = "获取文章信息内容", description = "获取文章内容")
    @GetMapping(value = "/getArticle", consumes = {MediaType.ALL_VALUE})
    public JsonResult<PostEntireVO> getArticle(){
        /*
            验证帖子状态是否合法(是否存在，状态可以访问)
            验证用户是否有该帖子的浏览权限(作者，用户，游客)
            返回帖子详细信息
         */
        return JsonResult.ok(null);

    }
}
