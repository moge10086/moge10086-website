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

    @Override
    public String toString() {
        return "BasePostVO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
