package com.moge10086.website.domain.vo.post;

import org.springframework.beans.BeanUtils;

/**
 * 文章编辑界面展示的数据
 * @author 22872
 */
public class ArticleEditVO extends BasePostEditVO {

    /** 文章内容 */
    private String articleContent ;

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
    public static ArticleEditVO initFromBase(BasePostEditVO basePostEditVO){
        ArticleEditVO articleEditVO =new ArticleEditVO();
        BeanUtils.copyProperties(basePostEditVO, articleEditVO);
        return articleEditVO;
    }
    @Override
    public String toString() {
        return "ArticleEditVO{" +
                "articleContent='" + articleContent + '\'' +
                "} " + super.toString();
    }
}
