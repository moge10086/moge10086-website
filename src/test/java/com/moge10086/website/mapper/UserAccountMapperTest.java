package com.moge10086.website.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserAccountMapperTest {
    @Resource
    UserAccountMapper userAccountMapper;
    @Test
    void getUserIdByEmailAndPwd() {
        System.out.println(userAccountMapper.getUserIdByEmailAndPwd("asdwdasd@qq.com","fb38a6269435ef1bf2318b63f3835580"));
    }

    @Test
    void updatePasswordById() {
        userAccountMapper.updatePasswordById(3L,"12345678123456781234567812345678",new Date());
    }
}