package com.moge10086.website.domain.query.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 22872
 */
public class QueryCommentReplyListBO {
    @Schema(description ="评论ID")
    @NotNull(message = "评论ID")
    private Long commentId;
    @Schema(description ="分页大小:最大20",defaultValue = "20")
    @Min(value = 1,message = "分页大小在1到20间")
    @Max(value = 20,message = "分页大小在1到20间")
    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    @Schema(description ="当前页数",defaultValue = "1")
    @Min(value = 1,message = "当前页数大于1")
    @NotNull(message = "currentPage不能为空")
    private Integer currentPage;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "QueryCommentReplyListBO{" +
                "commentId=" + commentId +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
