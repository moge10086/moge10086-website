package com.moge10086.website.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 邵权
 */
@Schema(name = "文章上传对象",description = "文章上传对象")
public class PostArticleBO extends PostBO {
    @Schema(description = "文章内容")
    @NotBlank(message = "文章内容不能为空")
    @Size(max = 50000,message = "内容长度超出")
    private String articleContent;

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "PostArticleBO{" +
                "articleContent='" + articleContent + '\'' +
                "} " + super.toString();
    }
}
