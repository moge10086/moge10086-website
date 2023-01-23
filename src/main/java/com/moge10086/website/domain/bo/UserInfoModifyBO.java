package com.moge10086.website.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 22872
 */
public class UserInfoModifyBO {
    @JsonIgnore
    private Long userId ;
    /** 用户名 */
    @Schema(description = "用户名")
    @Size(min = 1,max = 20,message = "用户名长度在1~20之间")
    @NotBlank(message = "用户名不能为空")
    private String userName ;
    /** 签名 */
    @Schema(description = "签名")
    @Size(max = 100,message = "签名不能超过100")
    private String sign ;
    /** 头像图片URL */
    @Schema(description = "头像图片URL")
    @Size(max = 2048,message = "头像URL长度不能超过2048")
    @NotBlank(message = "头像不能为空")
    private String avatarImg ;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    @Override
    public String toString() {
        return "UserInfoModifyBO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sign='" + sign + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                '}';
    }
}
