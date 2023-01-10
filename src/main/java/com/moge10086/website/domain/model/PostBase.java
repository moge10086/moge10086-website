package com.moge10086.website.domain.model;

import com.moge10086.website.domain.bo.PostBO;
import com.moge10086.website.enums.PostStatus;

import java.util.Date;
/**
 * 帖子基本信息;
 * @author : 邵权
 * @date : 2023-1-5
 */
public class PostBase{
    /** 帖子ID */
    private Long postId ;
    /** 作者ID */
    private Long authorId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;
    /** 帖子标题 */
    private String title ;
    /** 简介 */
    private String summary ;
    /** 封面图片URL */
    private String coverImg ;
    /** 帖子类别;文章、视频、交流等 */
    private Integer postType ;
    /** 帖子状态;草稿（0）、待审核（5）、上架（10）、下架（草稿）、封禁（15）、删除（-1） */
    private Integer postState ;

    public PostBase() {
    }

    public PostBase(Long postId, Long authorId, Date createTime, Date updateTime, String title, String summary, String coverImg, Integer postType, Integer postState) {
        this.postId = postId;
        this.authorId = authorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.summary = summary;
        this.coverImg = coverImg;
        this.postType = postType;
        this.postState = postState;
    }

    public static PostBase initPostBase(Long authorId, Integer postType, PostBO postBO){
        Date now = new Date();
        return new PostBase(null,authorId,now,now,
                postBO.getTitle(),postBO.getSummary(),postBO.getCoverImg(),
                postType, PostStatus.DRAFT.type);
    }
    /** 帖子ID */
    public Long getPostId(){
        return this.postId;
    }
    /** 帖子ID */
    public void setPostId(Long postId){
        this.postId=postId;
    }
    /** 作者ID */
    public Long getAuthorId(){
        return this.authorId;
    }
    /** 作者ID */
    public void setAuthorId(Long authorId){
        this.authorId=authorId;
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
    /** 帖子标题 */
    public String getTitle(){
        return this.title;
    }
    /** 帖子标题 */
    public void setTitle(String title){
        this.title=title;
    }
    /** 简介 */
    public String getSummary(){
        return this.summary;
    }
    /** 简介 */
    public void setSummary(String summary){
        this.summary=summary;
    }
    /** 封面图片URL */
    public String getCoverImg(){
        return this.coverImg;
    }
    /** 封面图片URL */
    public void setCoverImg(String coverImg){
        this.coverImg=coverImg;
    }
    /** 帖子类别;文章、视频、交流等 */
    public Integer getPostType(){
        return this.postType;
    }
    /** 帖子类别;文章、视频、交流等 */
    public void setPostType(Integer postType){
        this.postType=postType;
    }
    /** 帖子状态;草稿（0）、待审核（5）、上架（10）、下架（草稿）、封禁（15）、删除（-1） */
    public Integer getPostState(){
        return this.postState;
    }
    /** 帖子状态;草稿（0）、待审核（5）、上架（10）、下架（草稿）、封禁（15）、删除（-1） */
    public void setPostState(Integer postState){
        this.postState=postState;
    }
}