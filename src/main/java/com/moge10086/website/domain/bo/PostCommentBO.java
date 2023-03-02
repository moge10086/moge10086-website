package com.moge10086.website.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(name = "帖子评论对象",description = "帖子评论对象")
public class PostCommentBO {
    @JsonIgnore
    Long userId;
    @Schema(description = "帖子ID")
    @NotNull(message = "帖子ID不能为空")
    Long postId;
    @Schema(description = "被回复评论ID")
    Long repliedCommentId;
    @Schema(description = "评论内容")
    @NotBlank(message = "评论不能为空")
    @Size(min=1,max=500,message = "标题长度为1到500")
    String commentContent;

    public PostCommentBO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRepliedCommentId() {
        return repliedCommentId;
    }

    public void setRepliedCommentId(Long repliedCommentId) {
        this.repliedCommentId = repliedCommentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "PostCommentBO{" +
                "userId=" + userId +
                ", postId=" + postId +
                ", repliedCommentId=" + repliedCommentId +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
