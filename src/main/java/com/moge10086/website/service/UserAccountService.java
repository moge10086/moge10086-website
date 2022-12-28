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
}
