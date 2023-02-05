package com.moge10086.website.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.query.qo.QueryPostCardListBO;
import com.moge10086.website.domain.vo.post.ArticleShowVO;
import com.moge10086.website.domain.vo.post.PostCardVO;
import com.moge10086.website.service.PostManageService;
import com.moge10086.website.service.PostShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author 22872
 */
@Tag(name = "帖子展示相关", description = "帖子展示相关")
@RestController
@RequestMapping("postShow")
@Validated
public class PostShowController {
    @Resource
    PostManageService postManageService;
    @Resource
    PostShowService postShowService;
    @Operation(summary = "获取帖子卡片展示列表", description = "获取帖子展示信息列表，用于卡片，列表等简约展示")
    @PostMapping(value = "/getPostCardList",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Page<PostCardVO>> getPostCardList(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @RequestBody @Valid QueryPostCardListBO queryPostCardListBO){
        /* 验证用户是否有该帖子的浏览权限(作者，用户，游客)
            返回帖子卡片信息
            帖子信息：帖子ID、标题、封面、发布时间、类别、简介；浏览量、点赞、收藏、评论；
            作者信息：作者ID、头像、名称 */
        if (!queryPostCardListBO.isValid()){
            return JsonResult.errorMsg(StatusCode.ERROR_QUERY_ARGUMENT,"非法的查询参数");
        }
        Page<PostCardVO> postCardsPage = postShowService.listPostCards(queryPostCardListBO);
        return JsonResult.ok(postCardsPage);

    }

    @Operation(summary = "获取文章详细信息", description = "获取文章详细信息")
    @GetMapping(value = "/getArticle", consumes = {MediaType.ALL_VALUE})
    public JsonResult<ArticleShowVO> getArticle(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) @NotBlank String token,
            @Parameter(description = "帖子ID", required = true)
            @RequestParam Long postId){
        /*  验证帖子状态是否合法(是否存在，状态可以访问)
            验证用户是否有该帖子的浏览权限(作者，用户，游客)
            返回帖子详细信息
            帖子信息：帖子ID、标题、封面、发布时间、类别、简介；浏览量、点赞、收藏、评论；是否点赞、收藏；
            作者信息：作者ID、头像、名称、简介 */
        Long userId= JwtUtils.getUserIdFromToken(token);
        //查询该帖子能否被浏览
        if (!postShowService.validateShowPermissionByUserIdAndPostId(userId,postId)){
            return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
        }
        return JsonResult.ok(postShowService.getArticleShow(userId,postId));
    }
}
