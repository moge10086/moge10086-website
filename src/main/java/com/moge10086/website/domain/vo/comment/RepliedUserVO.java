package com.moge10086.website.domain.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author 22872
 */
public class RepliedUserVO {
    @Schema(description = "被回复用户ID")
    private Long userId ;
    @Schema(description = "被回复用户名")
    private String userName ;

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


    @Override
    public String toString() {
        return "RepliedUserVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
