package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.PostComment;
import com.moge10086.website.domain.vo.comment.PostCommentVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 22872
 */
public interface PostCommentMapper extends BaseMapper<PostComment> {
    /**
     * 查询评论信息
     * @param commentId
     * @return
     */
    PostCommentVO getPostCommentByCommentId(Long commentId);
    /**
     * 插入根评论
     * @param postComment
     * @return Boolean
     */
    Boolean insertRootComment(PostComment postComment);
    /**
     * 插入回复
     * @param postComment
     * @return Boolean
     */
    Boolean insertReplyComment(PostComment postComment);
    /**
     * 设置根评论的RootAndReplied字段
     * @param postCommentId
     * @return
     */
    @Update("UPDATE post_comment " +
            "SET root_comment_id=comment_id,replied_comment_id=comment_id " +
            "WHERE comment_id=#{postCommentId}")
    Boolean updateRootAndReplied(Long postCommentId);

    /**
     * 获得评论状态
     * @param commentId
     * @return
     */
    @Select("select comment_state from post_comment where comment_id = #{commentId}")
    Integer getCommentStateByCommentId(Long commentId);
    /**
     * 获得评论对应帖子id
     * @param commentId
     * @return
     */
    @Select("select post_id from post_comment where comment_id = #{commentId}")
    Long getPostIdByCommentId(Long commentId);
    /**
     * 获得评论对应根评论
     * @param commentId
     * @return
     */
    @Select("select root_comment_id from post_comment where comment_id = #{commentId}")
    Long getRootCommentIdByCommentId(Long commentId);
}
