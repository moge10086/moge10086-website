package com.moge10086.website.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sq
 */
@Schema(name = "用户信息",description = "")
@TableName("user_info")
public class UserInfo implements Serializable,Cloneable{
    /** 用户ID */
    @Schema(name = "用户ID",description = "")
    @TableId
    private Long userId ;
    /** 用户名 */
    @Schema(name = "用户名",description = "")
    private String userName ;
    /** 创建时间 */
    @Schema(name = "创建时间",description = "")
    private Date createTime ;
    /** 更新时间 */
    @Schema(name = "更新时间",description = "")
    private Date updateTime ;
    /** 签名 */
    @Schema(name = "签名",description = "")
    private String sign ;
    /** 头像图片URL */
    @Schema(name = "头像图片URL",description = "")
    private String avatarImg ;
    /** 身份 */
    @Schema(name = "身份",description = "")
    private Integer role ;

    /** 用户ID */
    public Long getUserId(){
        return this.userId;
    }
    /** 用户ID */
    public void setUserId(Long userId){
        this.userId=userId;
    }
    /** 用户名 */
    public String getUserName(){
        return this.userName;
    }
    /** 用户名 */
    public void setUserName(String userName){
        this.userName=userName;
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
    /** 签名 */
    public String getSign(){
        return this.sign;
    }
    /** 签名 */
    public void setSign(String sign){
        this.sign=sign;
    }
    /** 头像图片URL */
    public String getAvatarImg(){
        return this.avatarImg;
    }
    /** 头像图片URL */
    public void setAvatarImg(String avatarImg){
        this.avatarImg=avatarImg;
    }
    /** 身份 */
    public Integer getRole(){
        return this.role;
    }
    /** 身份 */
    public void setRole(Integer role){
        this.role=role;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sign='" + sign + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                ", role=" + role +
                '}';
    }
}