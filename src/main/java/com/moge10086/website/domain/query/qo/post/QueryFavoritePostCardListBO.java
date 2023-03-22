package com.moge10086.website.domain.query.qo.post;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 查询个人作品帖子列表
 * @author 22872
 */
public class QueryFavoritePostCardListBO extends BaseQueryPostListBO {
    @Schema(description ="用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QueryFavoritePostCardListBO{" +
                "userId=" + userId +
                "} " + super.toString();
    }
}
