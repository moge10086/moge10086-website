package com.moge10086.website.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 22872
 */
@TableName("user_follow")
public class UserFollow implements Serializable {
    /**
     * 关注id
     */
    @TableId(type= IdType.AUTO)
    private Long userFollowId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 被关注用户id
     */
    private Long followedUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 关注状态
     */
    private Integer followState;

    public static UserFollow initUserFollow(Long userId,Long followedUserId){
        Date now = new Date();
        return new UserFollow(null,userId,followedUserId,now,now,1);
    }
    public UserFollow() {
    }

    public UserFollow(Long userFollowId, Long userId, Long followedUserId, Date createTime, Date updateTime, Integer followState) {
        this.userFollowId = userFollowId;
        this.userId = userId;
        this.followedUserId = followedUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.followState = followState;
    }

    public Long getUserFollowId() {
        return userFollowId;
    }

    public void setUserFollowId(Long userFollowId) {
        this.userFollowId = userFollowId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(Long followedUserId) {
        this.followedUserId = followedUserId;
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

    public Integer getFollowState() {
        return followState;
    }

    public void setFollowState(Integer followState) {
        this.followState = followState;
    }

    @Override
    public String toString() {
        return "UserFollow{" +
                "userFollowId=" + userFollowId +
                ", userId=" + userId +
                ", followedUserId=" + followedUserId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", followState=" + followState +
                '}';
    }
}
