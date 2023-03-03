package com.moge10086.website.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.jwt.JwtUtils;
import com.moge10086.website.common.utils.JsonResult;
import com.moge10086.website.domain.bo.PostCommentBO;
import com.moge10086.website.domain.query.qo.QueryCommentReplyListBO;
import com.moge10086.website.domain.query.qo.QueryPostCommentListBO;
import com.moge10086.website.domain.vo.comment.PostCommentVO;
import com.moge10086.website.domain.vo.comment.RootPostCommentVO;
import com.moge10086.website.service.PostCommentService;
import com.moge10086.website.service.PostShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author sq
 */
@Tag(name = "帖子评论相关", description = "帖子评论相关")
@RestController
@RequestMapping("postComment")
@Validated
public class PostCommentController {
    @Resource
    PostCommentService postCommentService;
    @Resource
    PostShowService postShowService;
    @Operation(summary = "发表评论", description = "发表评论")
    @PostMapping(value = "/publishComment",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<PostCommentVO> publishComment(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = true) String token,
            @Parameter(description = "评论内容", required = true)
            @Valid @RequestBody PostCommentBO postCommentBO){
        /* 1.校验参数合法性（token，postId，repliedCommentId）
           2.根据repliedCommentId是否为null选择评论类型
           如果repliedCommentId为null则发表根评论（commentId==rootCommentId）
           如果repliedCommentId不为空则rootCommentId等于被回复评论的rootCommentI
        */
        Long userId = JwtUtils.getUserIdFromToken(token);
        //验证帖子id合法性
        if (!postShowService.validateShowPermissionByUserIdAndPostId(userId,postCommentBO.getPostId())){
            return JsonResult.errorMsg(StatusCode.ERROR_POST,"非法的postId");
        }
        //验证被回复id的合法性（存在、未被删除）
        if(postCommentBO.getRepliedCommentId()!=null){
            if(!postCommentService.validatePostComment(postCommentBO.getRepliedCommentId())){
                return JsonResult.errorMsg(StatusCode.ERROR_COMMENT,"非法的CommentID");
            }
        }
        postCommentBO.setUserId(userId);
        Long commentId = postCommentService.publishPostComment(postCommentBO);
        //返回本条评论内容
        PostCommentVO postCommentVO=postCommentService.getPostCommentByCommentId(commentId);
        return JsonResult.ok(postCommentVO);
    }
    @Operation(summary = "获得帖子展示评论", description = "获得帖子评论")
    @PostMapping(value = "/getPostComments",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Page<RootPostCommentVO>> getPostComments(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @Parameter(description = "帖子评论查询体", required = true)
            @Valid @RequestBody QueryPostCommentListBO queryPostCommentListBO){
        Page<RootPostCommentVO> rootPostCommentPage = postCommentService.listRootPostComment(queryPostCommentListBO);
        return JsonResult.ok(rootPostCommentPage);
    }
    @Operation(summary = "获得评论回复列表（子评论）", description = "获得评论回复列表（子评论）")
    @PostMapping(value = "/getCommentReplies",consumes = {"application/json; charset=UTF-8"})
    public JsonResult<Page<PostCommentVO>> getCommentReplies(
            @Parameter(description = "token")
            @RequestHeader(value = "Authorization",required = false) String token,
            @Parameter(description = "子评论查询体", required = true)
            @Valid @RequestBody QueryCommentReplyListBO queryCommentReplyListBO){
        Page<PostCommentVO> postCommentReplyPage = postCommentService.listPostCommentReply(queryCommentReplyListBO);
        return JsonResult.ok(postCommentReplyPage);
    }
}
