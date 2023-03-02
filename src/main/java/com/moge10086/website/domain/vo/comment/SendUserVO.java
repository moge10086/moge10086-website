package com.moge10086.website.domain.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;

public class SendUserVO {
    @Schema(description = "发表用户ID")
    private Long userId ;
    @Schema(description = "发表用户名")
    private String userName ;
    @Schema(description = "发表用户签名")
    private String sign ;
    @Schema(description = "发表用户头像图片URL")
    private String avatarImg ;
    @Schema(description = "发表角色")
    private Integer role ;

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SendUserVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sign='" + sign + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                ", role=" + role +
                '}';
    }
}
