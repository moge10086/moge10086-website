package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moge10086.website.domain.model.UserAccount;
import com.moge10086.website.mapper.UserAccountMapper;
import com.moge10086.website.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sq
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    @Resource
    UserAccountMapper userAccountMapper;

    @Override
    public Boolean emailIsExist(String email) {
        return userAccountMapper.getUserIdByEmail(email) != null;
    }
}
