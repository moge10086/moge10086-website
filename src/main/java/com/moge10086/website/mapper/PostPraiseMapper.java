package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.PostPraise;
import org.apache.ibatis.annotations.Select;

/**
 * @author 22872
 */
public interface PostPraiseMapper extends BaseMapper<PostPraise> {
    /**
     * 返回对应用户、帖子的点赞状态
     * @param userId
     * @param postId
     * @return
     */
    @Select("select praise_state from post_praise where post_id=#{postId} and user_id=#{userId}")
    Integer getPraiseState(Long userId,Long postId);
}
