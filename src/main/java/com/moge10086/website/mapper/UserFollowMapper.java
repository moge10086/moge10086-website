package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.model.UserFollow;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author sq
 */
public interface UserFollowMapper extends BaseMapper<UserFollow> {
    /**
     * 获得用户关注关系
     * @param userId
     * @param followedUserId
     * @return
     */
    @Select("select follow_state from user_follow where user_id = #{userId} AND followed_user_id = #{followedUserId} ")
    Integer getFollowStateByUserId(Long userId,Long followedUserId);

    /**
     * 更新用户关注状态
     * @param userId
     * @param followedUserId
     * @param followState
     * @param updateTime
     * @return
     */
    @Update("update user_follow set follow_state=#{followState},update_time=#{updateTime} where user_id=#{userId} and followed_user_id=#{followedUserId}")
    Boolean updateFollowState(Long userId, Long followedUserId, Integer followState, Date updateTime);

    /**
     * 获得关注用户的粉丝用户ID
     * @param followedUserId
     * @return
     */
    @Select("select user_id from user_follow where followed_user_id = #{followedUserId}")
    Page<Long> listFanUserId(Page<Long> page, Long followedUserId);

    /**
     * 获得用户关注用户的用户id
     * @param userId
     * @return
     */
    @Select("select followed_user_id from user_follow where user_id=#{userId}")
    Page<Long> listFollowedUserId(Page<Long> page,Long userId);
}
