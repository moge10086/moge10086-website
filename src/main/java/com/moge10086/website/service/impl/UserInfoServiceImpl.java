package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moge10086.website.domain.model.UserInfo;
import com.moge10086.website.mapper.UserInfoMapper;
import com.moge10086.website.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author sq
 */
@Service
public class UserInfoServiceImpl  extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
