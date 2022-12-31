package com.moge10086.website.service;

import com.moge10086.website.domain.dto.EmailCode;

import java.util.concurrent.TimeUnit;

/**
 * @author 22872
 */
public interface EmailCodeRedisService {
    String PREFIX ="EmailCode:";

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    Boolean hasKey(String key);
    /**
     * 设置redis 键值对
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    void set(String key, EmailCode value, Integer time, TimeUnit timeUnit);

    /**
     * 更新redis键值对,不改变过期时间
     * @param key
     * @param value
     */
    void set(String key, EmailCode value);

    /**
     * 取出redis 键值对
     * @param key
     * @return
     */
    EmailCode get(String key);

    /**
     * 获得key剩余过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);
}
