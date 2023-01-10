package com.moge10086.website.domain.dto.post;

import com.moge10086.website.domain.bo.PostArticleBO;
import org.springframework.beans.BeanUtils;

/**
 * 文章更新时传递的对象，包含需要更新的属性
 * @author 22872
 */
public class EditPostArticleDTO extends EditPostDTO{
    /** 文章内容 */
    private String articleContent;

    public EditPostArticleDTO() {
    }

    public EditPostArticleDTO(String articleContent) {
        this.articleContent = articleContent;
    }

    public static EditPostArticleDTO initEditPostArticleDTO(PostArticleBO postArticleBO){
        EditPostArticleDTO editPostArticleDTO = new EditPostArticleDTO();
        EditPostDTO editPostDTO = EditPostDTO.initEditPostDTO(postArticleBO);
        BeanUtils.copyProperties(editPostDTO,editPostArticleDTO);
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
