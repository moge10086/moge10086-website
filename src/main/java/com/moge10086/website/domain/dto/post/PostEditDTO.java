package com.moge10086.website.domain.dto.post;

import com.moge10086.website.domain.bo.PostBO;
import com.moge10086.website.enums.PostState;

import java.util.Date;

/**
 * 帖子更新时传递给数据库的对象，包含需要更新的属性
 * @author 22872
 */
public class PostEditDTO {
    /** 帖子ID */
    private Long postId ;
    /** 更新时间 */
    private Date updateTime ;
    /** 帖子标题 */
    private String title ;
    /** 简介 */
    private String summary ;
    /** 封面图片URL */
    private String coverImg ;
    /** 帖子状态;草稿（0）、待审核（5）、上架（10）、下架（草稿）、封禁（15）、删除（-1） */
    private Integer postState ;

    public Long getPostId() {
        return postId;
    }

    public PostEditDTO() {
    }

    public PostEditDTO(Long postId, Date updateTime, String title, String summary, String coverImg, Integer postState) {
        this.postId = postId;
        this.updateTime = updateTime;
        this.title = title;
        this.summary = summary;
        this.coverImg = coverImg;
        this.postState = postState;
    }

    public static PostEditDTO initEditPostDTO(PostBO postBO) {
        return new PostEditDTO(postBO.getPostId(),new Date(),postBO.getTitle(),postBO.getSummary(),postBO.getCoverImg(), PostState.DRAFT.type);
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getPostState() {
        return postState;
    }

    public void setPostState(Integer postState) {
        this.postState = postState;
    }

    @Override
    public String toString() {
        return "EditPostDTO{" +
                "postId=" + postId +
                ", updateTime=" + updateTime +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", postState=" + postState +
                '}';
    }
}
