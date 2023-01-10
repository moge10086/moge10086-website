package com.moge10086.website.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sq
 */
@TableName("user_account")
public class UserAccount implements Serializable{
    /** 用户ID */
    @TableId
    private Long userId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;
    /** 邮箱 */
    private String email ;
    /** 密码 */
    private String password ;

    public UserAccount() {
    }

    public UserAccount(Date createTime, Date updateTime, String email, String password) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.email = email;
        this.password = password;
    }

    public static UserAccount initUserAccount(String email, String password){
        Date now = new Date();
        return new UserAccount(now,now,email,password);
    }
    /** 用户ID */
    public Long getUserId(){
        return this.userId;
    }
    /** 用户ID */
    public void setUserId(Long userId){
        this.userId=userId;
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
    /** 邮箱 */
    public String getEmail(){
        return this.email;
    }
    /** 邮箱 */
    public void setEmail(String email){
        this.email=email;
    }
    /** 密码 */
    public String getPassword(){
        return this.password;
    }
    /** 密码 */
    public void setPassword(String password){
        this.password=password;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
