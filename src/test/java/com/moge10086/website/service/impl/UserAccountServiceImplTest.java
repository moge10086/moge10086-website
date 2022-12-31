package com.moge10086.website.service.impl;

import com.moge10086.website.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest
class UserAccountServiceImplTest {
    @Resource
    UserAccountService userAccountService;
    @Test
    void registerUser() {
        userAccountService.registerUser("adsd@asd.asd","12345678123456781234567812345678");
    }
}