package com.moge10086.website.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码加密相关类
 * @author 22872
 */
public class PasswordUtils {
    /**
     * 密码加密前缀
     */
    static public String MD5_PREFIX="kp65";
    static public String md5Password(String password){
        return DigestUtils.md5Hex(MD5_PREFIX+password);
    }
}
