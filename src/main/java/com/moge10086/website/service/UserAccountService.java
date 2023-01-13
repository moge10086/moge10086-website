package com.moge10086.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moge10086.website.domain.model.UserAccount;

/**
 * @author sq
 */
public interface UserAccountService extends IService<UserAccount> {
    /**
     * 查询邮箱是否已存在,存在则返回true
     * @param email
     * @return
     */
    Boolean emailIsExist(String email);

    /**
     * 保存用户账户信息，返回数据库生成的用户ID
     * @param userAccount
     * @return
     */
    Long saveUserAccount(UserAccount userAccount);

    /**
     * 注册用户,插入UserAccount、UserInfo，返回用户ID
     * @param email
     * @param password
     * @return
     */
    Long registerUser(String email,String password);
    /**
     * 通过邮箱和密码来登录，返回用户id或null
     * @param email
     * @param password
     * @return Long
     */
    Long loginByEmailAndPwd(String email, String password);

    /**
     * 重置密码
     *
     * @param email
     * @param password
     * @return Long
     */
    Long resetPassword(String email, String password);
    /**
     * 如果邮箱不存在则注册、存在则重置密码
     *
     * @param email
     * @param password
     * @return Long
     */
    Long registerOrReset(String email, String password);


}
