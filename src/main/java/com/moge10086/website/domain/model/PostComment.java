package com.moge10086.website.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moge10086.website.domain.bo.PostCommentBO;
import com.moge10086.website.enums.CommentState;

import java.util.Date;

/**
 * @author 22872
 */
@TableName("post_comment")
public class PostComment {
    /**
     * 评论ID
     */
    @TableId
    private Long commentId;
    /**
     * 根评论ID
     */
    private Long rootCommentId;
    /**
     * 回复评论ID
     */
    private Long repliedCommentId;
    /**
     * 帖子ID
     */
    private Long postId;
    /**
     * 发表用户ID
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论状态
     */
    private Integer commentState;

    public static PostComment initReplyComment(Long rootCommentId,PostCommentBO postCommentBO){
        Date now=new Date();
        return new PostComment(
                null,
                rootCommentId,
                postCommentBO.getRepliedCommentId(),
                postCommentBO.getPostId(),
                postCommentBO.getUserId(),
                now,
                now,
                postCommentBO.getCommentContent(),
                CommentState.SHOW.type);
    }
    public static PostComment initRootComment(PostCommentBO postCommentBO){
        Date now=new Date();
        return new PostComment(
                null,
                null,
                null,
                postCommentBO.getPostId(),
                postCommentBO.getUserId(),
                now,
                now,
                postCommentBO.getCommentContent(),
                CommentState.SHOW.type);
    }
    public PostComment() {
    }

    public PostComment(Long commentId, Long rootCommentId, Long repliedCommentId, Long postId, Long userId, Date createTime, Date updateTime, String commentContent, Integer commentState) {
        this.commentId = commentId;
        this.rootCommentId = rootCommentId;
        this.repliedCommentId = repliedCommentId;
        this.postId = postId;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.commentContent = commentContent;
        this.commentState = commentState;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Long rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    public Long getRepliedCommentId() {
        return repliedCommentId;
    }

    public void setRepliedCommentId(Long repliedCommentId) {
        this.repliedCommentId = repliedCommentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }
}
