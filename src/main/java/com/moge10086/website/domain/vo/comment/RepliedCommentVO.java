package com.moge10086.website.domain.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author 22872
 */
public class RepliedCommentVO {
    @Schema(description = "被回复评论ID")
    private Long commentId;
    @Schema(description = "被回复评论内容")
    private String commentContent;
    @Schema(description = "被回复评论状态")
    private Integer commentState;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
        return "RepliedCommentVO{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentState=" + commentState +
                '}';
    }
}
