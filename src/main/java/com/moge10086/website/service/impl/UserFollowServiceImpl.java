package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.model.UserFollow;
import com.moge10086.website.domain.query.qo.user.QueryFanListBO;
import com.moge10086.website.domain.query.qo.user.QueryFollowedListBO;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.mapper.UserFollowMapper;
import com.moge10086.website.mapper.UserQueryMapper;
import com.moge10086.website.service.UserFollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 22872
 */
@Service
public class UserFollowServiceImpl implements UserFollowService {
    @Resource
    UserFollowMapper userFollowMapper;
    @Resource
    UserQueryMapper userQueryMapper;
    @Override
    public Boolean followUser(Long userId, Long followedUserId) {
        //表中无记录
        Integer originalFollowState = userFollowMapper.getFollowStateByUserId(userId, followedUserId);
        if (originalFollowState==null){
            userFollowMapper.insert(UserFollow.initUserFollow(userId,followedUserId));
        }else{
            boolean followState=originalFollowState==0;
            userFollowMapper.updateFollowState(userId,followedUserId,followState?1:0,new Date());
            return followState;
        }
        return true;
    }

    @Override
    public Boolean getFollowState(Long userId, Long followedUserId) {
        Integer followState = userFollowMapper.getFollowStateByUserId(userId, followedUserId);
        return followState != null && (followState == 1);
    }

    @Override
    public Page<BaseUserVO> listFans(QueryFanListBO queryFanListBO) {
        Page<Long> fanIdPage=Page.of(queryFanListBO.getCurrentPage(), queryFanListBO.getPageSize());
        userFollowMapper.listFanUserId(fanIdPage,queryFanListBO.getFollowedUserId());
        List<Long> fanIds = fanIdPage.getRecords();
        Map<Long, BaseUserVO> baseUserMap = userQueryMapper.getBaseUsers(fanIds);
        return (Page<BaseUserVO>)fanIdPage.convert(n-> baseUserMap.get(n));
    }

    @Override
    public Page<BaseUserVO> listFollowed(QueryFollowedListBO queryFollowedListBO) {
        Page<Long> followedIdPage=Page.of(queryFollowedListBO.getCurrentPage(), queryFollowedListBO.getPageSize());
        userFollowMapper.listFollowedUserId(followedIdPage,queryFollowedListBO.getUserId());
        List<Long> followedIds = followedIdPage.getRecords();
        Map<Long, BaseUserVO> baseUserMap = userQueryMapper.getBaseUsers(followedIds);
        return (Page<BaseUserVO>)followedIdPage.convert(n-> baseUserMap.get(n));
    }


}
