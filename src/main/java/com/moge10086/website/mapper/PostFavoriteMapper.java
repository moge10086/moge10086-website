package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.PostFavorite;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author 22872
 */
public interface PostFavoriteMapper extends BaseMapper<PostFavorite> {
    /**
     * 返回对应用户、帖子的收藏状态
     * @param userId
     * @param postId
     * @return
     */
    @Select("select favorite_state from post_favorite where post_id=#{postId} and user_id=#{userId}")
    Integer getFavoriteState(Long userId,Long postId);

    /**
     * 更新帖子收藏信息
     * @param userId
     * @param postId
     * @param favoriteState
     * @param updateTime
     * @return
     */
    @Update("update post_favorite set favorite_state=#{favoriteState},update_time=#{updateTime} where user_id=#{userId} and post_id=#{postId}")
    Boolean updateFavoriteState(Long userId, Long postId, Integer favoriteState, Date updateTime);
}
