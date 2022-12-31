package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.UserAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author sq
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    /**
     * 通过邮箱查询用户ID
     * @param email
     * @return
     */
    @Select("select user_id from user_account where email = #{email}")
    Long getUserIdByEmail(String email);

    /**
     * 向数据库插入UserAccount，返回生成的主键
     * @param userAccount
     * @return Long
     */
    Long insertUserAccount(UserAccount userAccount);

    /**
     * 通过邮箱和密码查询用户ID
     * @param email
     * @param password
     * @return
     */
    @Select("select user_id from user_account where email = #{email} and password = #{password}")
    Long getUserIdByEmailAndPwd(String email, String password);

    /**
     * 更新密码、更新时间
     * @param userId
     * @param password
     * @param updateTime
     * @return Boolean
     */
    @Update("update user_account set password = #{password},update_time = #{updateTime} where user_id=#{userId}")
    Boolean updatePasswordById(Long userId, String password, Date updateTime);
}