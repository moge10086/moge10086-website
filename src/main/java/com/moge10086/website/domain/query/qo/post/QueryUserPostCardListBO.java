package com.moge10086.website.domain.query.qo.post;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 查询个人作品帖子列表
 * @author 22872
 */
public class QueryUserPostCardListBO extends BaseQueryPostListBO {
    @Schema(description ="作者ID")
    @NotNull(message = "作者id不能为空")
    private Long authorId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "QueryUserPostCardListBO{" +
                "authorId=" + authorId +
                "} " + super.toString();
    }
}
