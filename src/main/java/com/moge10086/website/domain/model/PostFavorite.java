package com.moge10086.website.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 22872
 */
public class PostFavorite implements Serializable {
    /** 帖子收藏id */
    private Long postFavoriteId ;
    /** 帖子id */
    private Long postId ;
    /** 用户id */
    private Long userId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;
    /** 点赞状态 */
    private Integer favoriteState ;
    public static PostFavorite initPostFavorite(Long postId, Long userId){
        Date now=new Date();
        return new PostFavorite(null,postId,userId,now,now,1);
    }

    public PostFavorite() {
    }

    public PostFavorite(Long postFavoriteId, Long postId, Long userId, Date createTime, Date updateTime, Integer favoriteState) {
        this.postFavoriteId = postFavoriteId;
        this.postId = postId;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.favoriteState = favoriteState;
    }

    public Long getPostFavoriteId() {
        return postFavoriteId;
    }

    public void setPostFavoriteId(Long postFavoriteId) {
        this.postFavoriteId = postFavoriteId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFavoriteState() {
        return favoriteState;
    }

    public void setFavoriteState(Integer favoriteState) {
        this.favoriteState = favoriteState;
    }
}
