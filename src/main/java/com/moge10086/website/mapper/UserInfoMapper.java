package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.bo.UserInfoModifyBO;
import com.moge10086.website.domain.model.UserInfo;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import org.apache.ibatis.annotations.Update;

/**
 * @author sq
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 查询用户基本信息
     * @param userId
     * @return
     */
    BaseUserVO getUserVO(Long userId);

    /**
     * 更新用户基本信息
     * @param userInfoModifyBO
     * @return
     */
    @Update("UPDATE user_info SET user_name=#{userName},sign=#{sign},avatar_img=#{avatarImg} WHERE user_id=#{userId}")
    Boolean updateUserInfo(UserInfoModifyBO userInfoModifyBO);
}
