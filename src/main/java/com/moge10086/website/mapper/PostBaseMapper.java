package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.dto.post.EditPostDTO;
import com.moge10086.website.domain.model.PostBase;
import org.apache.ibatis.annotations.Select;

/**
 * @author 22872
 */
public interface PostBaseMapper extends BaseMapper<PostBase> {
    /**
     * 获取对应帖子的作者id
     * @param postId
     * @return
     */
    @Select("select author_id from post_base where post_id = #{postId}")
    Long getAuthorIdByPostId(Long postId);

    /**
     * 获取对应帖子状态
     * @param postId
     * @return
     */
    @Select("select post_state from post_base where post_id = #{postId}")
    Integer getPostStateByPostId(Long postId);

    /**
     * 向数据库插入PostBase，返回postId
     * @param postBase
     * @return
     */
    Long insertPostBase(PostBase postBase);

    /**
     * 更新编辑后的帖子
     * @param editPostDTO
     */
    void updatePostBase(EditPostDTO editPostDTO);
}
