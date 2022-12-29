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
        return redisTemplate.hasKey(prefix+key);
    }

    @Override
    public void set(String key, EmailCode value, Integer time, TimeUnit timeUnit) {
        ValueOperations<String,EmailCode> vo =redisTemplate.opsForValue();
        vo.set(prefix+key,value,time,timeUnit);
    }

    @Override
    public void set(String key, EmailCode value) {
        ValueOperations<String,EmailCode> vo =redisTemplate.opsForValue();
        vo.set(prefix+key,value);
    }

    @Override
    public EmailCode get(String key) {
        ValueOperations<String,EmailCode> vo =redisTemplate.opsForValue();
        return vo.get(prefix+key);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(prefix+key);
    }
}
