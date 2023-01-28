package com.moge10086.website.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.bo.PostArticleBO;
import com.moge10086.website.domain.qo.QueryPostManageListBO;
import com.moge10086.website.domain.vo.post.ArticleEditVO;
import com.moge10086.website.domain.vo.post.BasePostVO;
import com.moge10086.website.service.PostManageService;
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
@Tag(name = "帖子操作相关", description = "帖子操作相关")
@RestController
@RequestMapping("postManage")
@Validated
public class PostManageController {
    @Resource
    PostManageService postManageService;
    /**
     * @description 上传文章进行保存/编辑,如果成功则返回用户信息
     * @return JsonResult<Long>
     */
    @Operation(summary = "保存、编辑文章", description = "保存、编辑文章，如果有id则为编辑、无id则为保存，返回帖子id")
    @PostMapping(value = "/saveOrEditArticlePost",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Long> saveOrEditArticle(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "文章帖子内容", required = true)
            @Valid @RequestBody PostArticleBO postArticleBO){
        /*  参数校验，判断传入字段是否合法，由注解验证完成
            如果携带id的话：判断帖子id是否存在，是否属于该用户，状态是否合法（锁定，删除）
            通过id来选择执行保存、编辑功能
            返回保存后的帖子id
        */
        Long userId=JwtUtils.getUserIdFromToken(token);
        Long postId=postArticleBO.getPostId();
        if (postId==null){
            //保存
            postId= postManageService.savePostArticle(userId,postArticleBO);
        }else{
            //编辑，判断该帖子能否被编辑：是否归属于该用户，状态是否可以编辑
            if (!postManageService.validatePermissionByUserIdAndPostId(userId, postArticleBO.getPostId())){
                return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
            }
            postManageService.editPostArticle(userId,postArticleBO);
        }
        //返回id
        return JsonResult.ok(postId);
    }

    /**
     * 删除帖子
     * @param token
     * @param postId
     * @return
     */
    @Operation(summary = "删除帖子", description = "删除帖子，如果删除成功返回true反之为false")
    @PostMapping(value = "/deletePost",consumes = {"application/x-www-form-urlencoded; charset=UTF-8"})
    public JsonResult<Boolean> deletePost(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "帖子ID", required = true)
            @RequestParam Long postId){
        Long userId=JwtUtils.getUserIdFromToken(token);
        //判断该帖子是否归属于该用户，状态是否可以被删除 todo：使用validateDeletePermissionByUserIdAndPostId
        if (!postManageService.validatePermissionByUserIdAndPostId(userId, postId)){
            return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
        }
        //执行删除并返回结果
        return JsonResult.ok(postManageService.deletePost(postId));
    }

    /**
     * 发布帖子，设置帖子状态为审核/展示
     * @param token
     * @param postId
     * @return
     */
    @Operation(summary = "发布帖子", description = "发布帖子，使文章处于审核/展示状态")
    @PostMapping(value = "/publishPost",consumes = {"application/x-www-form-urlencoded; charset=UTF-8"})
    public JsonResult<Boolean> publishPost(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "帖子ID", required = true)
            @RequestParam Long postId){
        Long userId=JwtUtils.getUserIdFromToken(token);
        //判断该帖子是否归属于该用户，状态是否可以被发布
        if (!postManageService.validatePermissionByUserIdAndPostId(userId, postId)){
            return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
        }
        //发布并返回结果
        return JsonResult.ok(postManageService.publishPost(postId));
    }
    /**
     * 撤销（下架）帖子，设置帖子状态为草稿
     * @param token
     * @param postId
     * @return
     */
    @Operation(summary = "撤销帖子", description = "撤销帖子，使文章处于草稿状态")
    @PostMapping(value = "/cancelPost",consumes = {"application/x-www-form-urlencoded; charset=UTF-8"})
    public JsonResult<Boolean> cancelPost(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "帖子ID", required = true)
            @RequestParam Long postId){
        Long userId=JwtUtils.getUserIdFromToken(token);
        //判断该帖子是否归属于该用户，状态是否可以被撤销
        if (!postManageService.validatePermissionByUserIdAndPostId(userId, postId)){
            return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
        }
        //发布并返回结果
        return JsonResult.ok(postManageService.cancelPost(postId));
    }

    /**
     * 获取文章界面详细编辑信息
     * @param token
     * @param postId
     * @return
     */
    @Operation(summary = "获取文章界面详细编辑信息", description = "获取文章界面详细编辑信息")
    @PostMapping(value = "/getArticleEditView",consumes = {"application/x-www-form-urlencoded; charset=UTF-8"})
    public JsonResult<ArticleEditVO> getArticleEditView(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @Parameter(description = "帖子ID", required = true)
            @RequestParam Long postId){
        Long userId=JwtUtils.getUserIdFromToken(token);
        //判断该帖子是否归属于该用户，状态是否合法
        if (!postManageService.validatePermissionByUserIdAndPostId(userId, postId)){
            return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
        }
        ArticleEditVO articleEditVO = postManageService.getArticleEditView(postId);
        //发布并返回结果
        return JsonResult.ok(articleEditVO);
    }
    /**
     * 返回用户帖子管理展示列表
     */
    @Operation(summary = "返回用户帖子管理展示列表", description = "返回用户帖子管理展示列表")
    @PostMapping(value = "/getManagePostList",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Page<BasePostVO>> getManagePostList(
            @Parameter(description = "token", required = true)
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid QueryPostManageListBO queryPostManageListBO){
        Long userId=JwtUtils.getUserIdFromToken(token);
        queryPostManageListBO.setAuthorId(userId);
        if (!queryPostManageListBO.isValid()){
            return JsonResult.errorMsg(StatusCode.ERROR_QUERY_ARGUMENT,"非法的查询参数");
        }
        return JsonResult.ok(postManageService.getManagePostList(queryPostManageListBO));
    }
    /**
     * 点赞
     */
    /**
     * 收藏
     */

}
