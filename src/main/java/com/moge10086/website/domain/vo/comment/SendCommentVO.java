package com.moge10086.website.domain.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @author 22872
 */
public class SendCommentVO {
    @Schema(description = "评论ID")
    private Long commentId;
    @Schema(description = "根评论ID")
    private Long rootCommentId;
    @Schema(description = "帖子ID")
    private Long postId;
    @Schema(description = "发表时间")
    private Date updateTime;
    @Schema(description = "评论内容")
    private String commentContent;
    @Schema(description = "评论状态")
    private Integer commentState;

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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    @Override
    public String toString() {
        return "SendCommentVO{" +
                "commentId=" + commentId +
                ", rootCommentId=" + rootCommentId +
                ", postId=" + postId +
                ", updateTime=" + updateTime +
                ", commentContent='" + commentContent + '\'' +
                ", commentState=" + commentState +
                '}';
    }
}
