package com.moge10086.website.domain.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 22872
 * @describe 邮箱验证码对象
 */
public class EmailCode implements Serializable {
    /** 验证码错误次数上限 */
    public static final Integer CAPTCHA_ERROR_LIMIT=5;
    /** 验证码有效期 SECONDS(秒) */
    public static final Integer CAPTCHA_VALID_TIME=300;
    /** 多次请求间隔时间 SECONDS(秒) */
    public static final Integer SEND_EMAIL_INTERVAL=60;

    /**
     * 生成六位数字验证码
     * @return
     */
    public static String generateCode(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(100001,999999));
    }
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
