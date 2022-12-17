package com.moge10086.website.domain.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author sq
 */
@Schema(description = "用户登录信息")
public class UserLoginVO extends BaseUserVO {
    @Schema(description = "登录凭证")
    private String userTokenJwt;

    public String getUserTokenJwt() {
        return userTokenJwt;
    }

    public void setUserTokenJwt(String userTokenJwt) {
        this.userTokenJwt = userTokenJwt;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "userTokenJwt='" + userTokenJwt + '\'' +
                "} " + super.toString();
    }
}
