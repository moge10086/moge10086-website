package com.moge10086.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.query.qo.user.QueryFanListBO;
import com.moge10086.website.domain.query.qo.user.QueryFollowedListBO;
import com.moge10086.website.domain.vo.user.BaseUserVO;

/**
 * @author 22872
 */
public interface UserFollowService{
    /**
     * 关注用户
     * @param userId
     * @param followedUserId
     * @return
     */
    Boolean followUser(Long userId,Long followedUserId);

    /**
     * 获得用户关注状态
     * @param userId
     * @param followedUserId
     * @return
     */
    Boolean getFollowState(Long userId,Long followedUserId);

    /**
     * 获得用户粉丝列表
     * @param queryFanListBO
     * @return
     */
    Page<BaseUserVO> listFans(QueryFanListBO queryFanListBO);

    /**
     * 获得用户关注列表
     * @param queryFollowedListBO
     * @return
     */
    Page<BaseUserVO> listFollowed(QueryFollowedListBO queryFollowedListBO);

}
