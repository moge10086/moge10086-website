package com.moge10086.website.domain.vo.post;

import java.util.Date;

/**
 * 帖子列表展示基本信息
 * @author 22872
 */
public class BasePostVO {
    /** 帖子ID */
    private Long postId ;
    /** 帖子标题 */
    private String title ;
    /** 简介 */
    private String summary ;
    /** 封面图片URL */
    private String coverImg ;
    /** 更新时间 */
    private Date updateTime ;
    /** 帖子类别;文章、视频、交流等 */
    private Integer postType ;
    /** 帖子状态;草稿（0）、待审核（5）、上架（10）、下架（草稿）、封禁（15）、删除（-1） */
    private Integer postState ;
    /** 浏览量  */
    private Integer readCount ;
    /** 点赞数 */
    private Integer praiseCount ;
    /** 收藏数 */
    private Integer favoriteCount ;
    /** 评论数 */
    private Integer commentCount ;

    public Integer getPostState() {
        return postState;
    }

    public void setPostState(Integer postState) {
        this.postState = postState;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "BasePostVO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", updateTime=" + updateTime +
                ", postType=" + postType +
                ", postState=" + postState +
                ", readCount=" + readCount +
                ", praiseCount=" + praiseCount +
                ", favoriteCount=" + favoriteCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
