package com.moge10086.website.domain.dto;

import com.moge10086.website.service.EmailCodeRedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 22872
 * @describe 邮箱验证码对象
 */
@Component
public class EmailCode implements Serializable {
    public static EmailCodeRedisService emailCodeRedisService;
    /** 验证码错误次数上限 */
    public static final Integer CAPTCHA_ERROR_LIMIT=5;
    /** 验证码有效期 SECONDS(秒) */
    public static final Integer CAPTCHA_VALID_TIME=300;
    /** 多次请求间隔时间 SECONDS(秒) */
    public static final Integer SEND_EMAIL_INTERVAL=60;

    @Serial
    private static final long serialVersionUID = 1L;
    /** 邮箱 */
    private String email;
    /** 验证码 */
    private String code;
    /** 已请求次数 */
    private Integer count;

    public EmailCode() {
    }

    public EmailCode(String email) {
        this.email = email;
        this.code = generateCode();
        this.count = 0;
    }
    /**
     * 生成六位数字验证码
     * @return
     */
    public static String generateCode(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(100001,999999));
    }

    /**
     * 已校验完毕、废弃验证码；
     * 自动更新到redis里
     * 与删除不同、还保持着请求间隔的信息
     */
    public void finish(){
        this.count=CAPTCHA_ERROR_LIMIT;
        emailCodeRedisService.set(email,this);
    }
    /**
     * 判断验证码是否正确，正确返回true，错误返回false
     * 如果错误则count+1
     * @param emailCode
     * @return Boolean
     */
    public Boolean compareWithCode(String emailCode){
        if (code.equals(emailCode)){
            this.finish();
            return true;
        }
        //错误次数+1
        this.count++;
        //更新到redis里
        emailCodeRedisService.set(email,this);
        return false;
    }

    @Resource
    public void setEmailCodeRedisService(EmailCodeRedisService emailCodeRedisService) {
        EmailCode.emailCodeRedisService = emailCodeRedisService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EmailCode{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", count=" + count +
                '}';
    }
}
