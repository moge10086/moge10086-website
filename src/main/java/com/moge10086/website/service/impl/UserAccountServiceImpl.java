package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moge10086.website.domain.model.UserAccount;
import com.moge10086.website.domain.model.UserInfo;
import com.moge10086.website.mapper.UserAccountMapper;
import com.moge10086.website.mapper.UserInfoMapper;
import com.moge10086.website.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author sq
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    @Resource
    UserAccountMapper userAccountMapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public Boolean emailIsExist(String email) {
        return userAccountMapper.getUserIdByEmail(email) != null;
    }

    @Override
    public Long saveUserAccount(UserAccount userAccount) {
        return userAccountMapper.insertUserAccount(userAccount);
    }

    @Override
    @Transactional
    public Long registerUser(String email, String password) {
        //生成UserAccount实体
        UserAccount userAccount=UserAccount.initUserAccount(email,password);
        //执行插入SQL
        userAccountMapper.insertUserAccount(userAccount);
        //生成UserInfo实体
        UserInfo userInfo=UserInfo.initUserInfo(userAccount.getUserId());
        //执行插入SQL
        userInfoMapper.insert(userInfo);
        return userAccount.getUserId();
    }

    @Override
    public Long loginByEmailAndPwd(String email, String password) {
        return userAccountMapper.getUserIdByEmailAndPwd(email,password);
    }

    @Override
    public Long resetPassword(String email, String password) {
        Long userId=userAccountMapper.getUserIdByEmail(email);
        userAccountMapper.updatePasswordById(userId,password,new Date());
        return userId;
    }

    @Override
    public Long registerOrReset(String email, String password) {
        if (this.emailIsExist(email)){
            //如果已经被注册则重置密码
            return this.resetPassword(email, password);
        }else {
            //未被注册、注册用户
            return this.registerUser(email, password);
        }
    }
}
