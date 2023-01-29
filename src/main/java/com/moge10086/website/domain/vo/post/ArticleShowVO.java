package com.moge10086.website.domain.vo.post;

/**
 * 展示文章信息
 * @author sq
 */
public class ArticleShowVO {
    PostShowVO postShowVO;
    String articleContent;

    public PostShowVO getPostShowVO() {
        return postShowVO;
    }

    public ArticleShowVO() {
    }

    public ArticleShowVO(PostShowVO postShowVO, String articleContent) {
        this.postShowVO = postShowVO;
        this.articleContent = articleContent;
    }

    public void setPostShowVO(PostShowVO postShowVO) {
        this.postShowVO = postShowVO;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "ArticleShowVO{" +
                "postShowVO=" + postShowVO +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }
}
