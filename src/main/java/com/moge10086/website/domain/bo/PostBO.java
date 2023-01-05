package com.moge10086.website.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 邵权
 */
@Schema(name = "文章上传基本对象",description = "文章上传基本对象")
public class PostBO {
    /** 帖子id */
    @Schema(description = "帖子id",defaultValue = "1")
    private Long postId ;
    /** 标题 */
    @Schema(description = "标题",defaultValue = "title")
    @NotBlank(message = "标题不能为空")
    @Size(min=1,max=40,message = "标题长度需要在1和40之间")
    private String title;
    /** 本文内容 */
    @Schema(description = "简介",defaultValue = "summary")
    @Size(max=500,message = "summary内容不能大于500字")
    private String summary;
    /** 展示图片url */
    @Schema(description = "封面图片url",defaultValue = "https://yun.moge10086.top/1.png")
    @Size(max=1024,message = "链接长度不能超过1024")
    private String coverImg;

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
        return "PostBO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", coverImg='" + coverImg + '\'' +
                '}';
    }
}
