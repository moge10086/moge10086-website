package com.moge10086.website.domain.dto.post;

import com.moge10086.website.domain.bo.PostArticleBO;
import org.springframework.beans.BeanUtils;

/**
 * 文章更新时传递给数据库的对象，包含需要更新的属性
 * @author 22872
 */
public class PostArticleEditDTO extends PostEditDTO {
    /** 文章内容 */
    private String articleContent;

    public PostArticleEditDTO() {
    }

    public PostArticleEditDTO(String articleContent) {
        this.articleContent = articleContent;
    }

    public static PostArticleEditDTO initEditPostArticleDTO(PostArticleBO postArticleBO){
        PostArticleEditDTO editPostArticleDTO = new PostArticleEditDTO();
        PostEditDTO postEditDTO = PostEditDTO.initEditPostDTO(postArticleBO);
        BeanUtils.copyProperties(postEditDTO,editPostArticleDTO);
        editPostArticleDTO.setArticleContent(postArticleBO.getArticleContent());
        return editPostArticleDTO;
    }
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "EditPostArticleDTO{" +
                "articleContent='" + articleContent + '\'' +
                "} " + super.toString();
    }
}
