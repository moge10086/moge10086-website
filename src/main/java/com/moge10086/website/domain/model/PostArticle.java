package com.moge10086.website.domain.model;

import com.moge10086.website.domain.bo.PostArticleBO;

import java.util.Date;

/**
 * 文章帖子信息;
 * @author : 邵权
 * @date : 2023-1-9
 */
public class PostArticle{
    /** 帖子ID */
    private Long postId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;
    /** 文章内容 */
    private String articleContent ;

    public PostArticle() {
    }

    public PostArticle(Long postId, Date createTime, Date updateTime, String articleContent) {
        this.postId = postId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.articleContent = articleContent;
    }
    public static PostArticle initPostArticle(Long postId, PostArticleBO postArticleBO){
        Date now = new Date();
        return new PostArticle(postId,now,now,postArticleBO.getArticleContent());
    }
    /** 帖子ID */
    public Long getPostId(){
        return this.postId;
    }
    /** 帖子ID */
    public void setPostId(Long postId){
        this.postId=postId;
    }
    /** 创建时间 */
    public Date getCreateTime(){
        return this.createTime;
    }
    /** 创建时间 */
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    /** 更新时间 */
    public Date getUpdateTime(){
        return this.updateTime;
    }
    /** 更新时间 */
    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }
    /** 文章内容 */
    public String getArticleContent(){
        return this.articleContent;
    }
    /** 文章内容 */
    public void setArticleContent(String articleContent){
        this.articleContent=articleContent;
    }
}