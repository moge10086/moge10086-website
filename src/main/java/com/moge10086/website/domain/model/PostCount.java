package com.moge10086.website.domain.model;

/**
 * @author 22872
 */
public class PostCount {
    private Long postId ;
    /** 浏览量  */
    private Integer readCount ;
    /** 点赞数 */
    private Integer praiseCount ;
    /** 收藏数 */
    private Integer favoriteCount ;
    /** 评论数 */
    private Integer commentCount ;
    public static PostCount initPostCount(Long postId){
        return new PostCount(postId,0,0,0,0);
    }

    public PostCount(Long postId, Integer readCount, Integer praiseCount, Integer favoriteCount, Integer commentCount) {
        this.postId = postId;
        this.readCount = readCount;
        this.praiseCount = praiseCount;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
    }

    public PostCount() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
