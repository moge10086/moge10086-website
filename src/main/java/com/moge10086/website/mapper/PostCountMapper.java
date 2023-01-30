package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.PostCount;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 22872
 */
public interface PostCountMapper extends BaseMapper<PostCount> {
    /**
     * 查询对应帖子列表的计数信息
     * @param postIds
     * @return
     */
    List<PostCount> listPostCount(List<Long> postIds);

    /**
     * 查询对应帖子的计数信息
     * @param postId
     * @return
     */
    PostCount getPostCount(Long postId);

    /**
     * 浏览量+1
     * todo 缓存处理
     * @param postId
     * @return
     */
    @Update("update post_count set read_count = read_count + 1 where post_id = #{post_id}")
    Boolean readCountPlusOne(Long postId);

    /**
     * 点赞数+1
     * todo 缓存处理
     * @param postId
     * @return
     */
    @Update("update post_count set praise_count = praise_count + 1 where post_id = #{post_id}")
    Boolean praiseCountPlusOne(Long postId);

    /**
     * 点赞数-1
     * todo 缓存处理
     * @param postId
     * @return
     */
    @Update("update post_count set praise_count = praise_count - 1 where post_id = #{post_id}")
    Boolean praiseCountMinusOne(Long postId);

}
