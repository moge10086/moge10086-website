package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.UserAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    Long getUserIdByEmail(@Param("email") String email);
}
