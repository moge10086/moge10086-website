package com.moge10086.website.service.impl;

import com.moge10086.website.service.EmailCodeRedisService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class EmailCodeRedisServiceImplTest {

    @Resource
    EmailCodeRedisService emailCodeRedisService;

    String testEmail="test@test.test";
    @BeforeEach
    void setUp() {
    }
    @Test
    void set() {
    }

    @Test
    void get() {
    }


    @AfterEach
    void tearDown() {
    }

}