package com.moge10086.website.domain.vo.user;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author sq
 */
@Schema(description = "基础用户信息")
public class BaseUserVO {
    @Schema(description = "用户ID")
    private Long userId ;
    /** 用户名 */
    @Schema(description = "用户名")
    private String userName ;
    /** 签名 */
    @Schema(description = "签名")
    private String sign ;
    /** 头像图片URL */
    @Schema(description = "头像图片URL")
    private String avatarImg ;
    /** 身份 */
    @Schema(description = "角色")
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
        return "BaseUserVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sign='" + sign + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                ", role=" + role +
                '}';
    }
}
