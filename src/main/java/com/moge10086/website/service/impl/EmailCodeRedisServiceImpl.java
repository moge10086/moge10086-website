package com.moge10086.website.service.impl;

import com.moge10086.website.domain.dto.EmailCode;
import com.moge10086.website.service.EmailCodeRedisService;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 22872
 */
@Service
public class EmailCodeRedisServiceImpl implements EmailCodeRedisService {
    @Resource
    private RedisTemplate<String, EmailCode> redisTemplate;

    @Override
    public Boolean hasKey(String key) {
        //todo 使用切面编程消除PREFIX
        return redisTemplate.hasKey(PREFIX+key);
    }

    @Override
    public void set(String key, EmailCode value, Integer time, TimeUnit timeUnit) {
        ValueOperations<String,EmailCode> vo =redisTemplate.opsForValue();
        vo.set(PREFIX+key,value,time,timeUnit);
    }

    @Override
    public void set(String key, EmailCode value) {
        ValueOperations<String,EmailCode> vo =redisTemplate.opsForValue();
        //更新过期时间
        Long expire=redisTemplate.getExpire(PREFIX+key,TimeUnit.SECONDS);
        if (expire==null||expire<0){
            //取值时已经过期了则不再设置
            return;
        }
        vo.set(PREFIX+key,value,expire,TimeUnit.SECONDS);
    }

    @Override
    public EmailCode get(String key) {
        ValueOperations<String,EmailCode> vo =redisTemplate.opsForValue();
        return vo.get(PREFIX+key);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(PREFIX+key);
    }
}
