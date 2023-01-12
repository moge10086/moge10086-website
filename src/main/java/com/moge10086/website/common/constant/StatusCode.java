package com.moge10086.website.common.constant;

/**
 * ClassName: StatusCode
 * Description:
 *
 * @author sq
 * @date 2022/12/16 20:26
 */
public class StatusCode {

    /**
     * 用户邮箱已存在
     */
    static public int USER_EMAIL_EXISTS=111;
    /**
     * 业务成功
     */
    static public int OK=200;
    /**
     * 用户不存在或密码错误
     */
    static public int ERROR_LOGIN=210;

    /**
     * 非法的PostID
     */
    static public int ERROR_POST=221;
    /**
     * token失效或过期
     */
    static public int ERROR_TOKEN=311;
    /**
     * 验证码无效或过期
     */
    static public int INVALID_CAPTCHA=321;
    /**
     * 验证码错误
     */
    static public int ERROR_CAPTCHA=322;
    /**
     * 帖子类型错误
     */
    static public int ERROR_POST_TYPE=332;
    /**
     * 帖子状态错误
     */
    static public int ERROR_POST_STATUS=334;
    /**
     * 用户角色错误
     */
    static public int ERROR_ROLE=342;
    /**
     * 无效的输入
     */
    static public int ERROR_INPUT=402;
    /**
     * 请求资源不存在
     */
    static public int NO_FIND=404;

    /**
     * 无效的点赞
     */
    static public int ERROR_LIKE=412;
    /**
     * 该邮箱用户不存在
     */
    static public int NO_FIND_USER_EMAIL=414;
    /**
     * 验证码频繁请求
     */
    static public int REQUEST_FREQUENTLY=422;
    /**
     * 无效的关注
     */
    static public int ERROR_FOLLOW=432;
    /**
     * 错误的查询参数
     */
    static public int ERROR_QUERY_ARGUMENT=440;
    /**
     * 邮件发送错误
     */
    static public int ERROR_EMAIL=442;
    /**
     * 参数错误
     */
    static public int INVALID_ARGUMENT=445;
    /**
     * 未知的错误
     */
    static public int UNKNOWN_ERROR=500;
}
