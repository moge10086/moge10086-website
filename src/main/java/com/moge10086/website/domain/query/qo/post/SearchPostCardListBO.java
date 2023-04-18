package com.moge10086.website.domain.query.qo.post;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * @author 22872
 */
public class SearchPostCardListBO extends BaseQueryPostListBO{
    @Schema(description ="关键词")
    @NotBlank(message = "关键词不能为空")
    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "SearchPostCardListBO{" +
                "keywords='" + keywords + '\'' +
                "} " + super.toString();
    }
}
