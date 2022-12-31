package com.moge10086.website.domain.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;

/**
 * @author sq
 */
@Schema(description = "用户登录信息")
public class UserLoginVO extends BaseUserVO {
    @Schema(description = "登录凭证")
    private String userToken;

    public UserLoginVO() {
    }

    public UserLoginVO(BaseUserVO baseUserVO) {
        BeanUtils.copyProperties(baseUserVO, this);
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "userToken='" + userToken + '\'' +
                "} " + super.toString();
    }
}
