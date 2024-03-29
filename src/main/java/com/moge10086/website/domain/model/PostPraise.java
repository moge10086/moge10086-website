package com.moge10086.website.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author 22872
 */
@TableName("post_praise")
public class PostPraise {
    /** 帖子点赞ID */
    @TableId(type= IdType.AUTO)
    private Long postPraiseId ;
    /** 帖子ID */
    private Long postId ;
    /** 用户ID */
    private Long userId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;
    /** 点赞状态 */
    private Integer praiseState ;

    public PostPraise() {
    }
    public static PostPraise initPostPraise(Long postId, Long userId){
        Date now=new Date();
        return new PostPraise(null,postId,userId,now,now,1);
    }
    public PostPraise(Long postPraiseId, Long postId, Long userId, Date createTime, Date updateTime, Integer praiseState) {
        this.postPraiseId = postPraiseId;
        this.postId = postId;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.praiseState = praiseState;
    }

    public Long getPostPraiseId() {
        return postPraiseId;
    }

    public void setPostPraiseId(Long postPraiseId) {
        this.postPraiseId = postPraiseId;
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

    public Integer getPraiseState() {
        return praiseState;
    }

    public void setPraiseState(Integer praiseState) {
        this.praiseState = praiseState;
    }
}
