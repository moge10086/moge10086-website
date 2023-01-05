package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moge10086.website.domain.model.UserInfo;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.mapper.UserInfoMapper;
import com.moge10086.website.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sq
 */
@Service
public class UserInfoServiceImpl  extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public BaseUserVO getBaseUserVO(Long userId) {
        return userInfoMapper.getUserVO(userId);
    }
}
