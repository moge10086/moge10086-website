package com.moge10086.website.domain.vo.post;

/**
 * 帖子编辑界面展示的内容
 * @author 22872
 */
public class BasePostEditVO {
    /** 帖子ID */
    private Long postId ;
    /** 帖子标题 */
    private String title ;
    /** 简介 */
    private String summary ;
    /** 封面图片URL */
    private String coverImg ;

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

    @Override
    public String toString() {
        return "BasePostEditVO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", coverImg='" + coverImg + '\'' +
                '}';
    }
}
