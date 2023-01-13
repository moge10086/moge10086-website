package com.moge10086.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moge10086.website.domain.bo.UserInfoModifyBO;
import com.moge10086.website.domain.model.UserInfo;
import com.moge10086.website.domain.vo.user.BaseUserVO;

/**
 * @author sq
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取用户基本信息
     * @param userId
     * @return
     */
    BaseUserVO getBaseUserVO(Long userId);

    /**
     * 更新用户基本信息
     * @param userInfoModifyBO
     * @return
     */
    Boolean updateUserInfo(UserInfoModifyBO userInfoModifyBO);
}
