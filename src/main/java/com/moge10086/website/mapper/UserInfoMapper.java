package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.UserAccount;
import com.moge10086.website.domain.model.UserInfo;
import com.moge10086.website.domain.vo.user.BaseUserVO;

/**
 * @author sq
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 查询用户基本信息
     * @param userId
     * @return
     */
    BaseUserVO getUserVO(Long userId);
}
