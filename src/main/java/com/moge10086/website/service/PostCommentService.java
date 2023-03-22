package com.moge10086.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.bo.PostCommentBO;
import com.moge10086.website.domain.query.qo.comment.QueryCommentReplyListBO;
import com.moge10086.website.domain.query.qo.comment.QueryPostCommentListBO;
import com.moge10086.website.domain.vo.comment.PostCommentVO;
import com.moge10086.website.domain.vo.comment.RootPostCommentVO;

/**
 * @author 22872
 */
public interface PostCommentService {
    /**
     * 获得评论列表
     * @param qo
     * @return
     */
    Page<RootPostCommentVO> listRootPostComment(QueryPostCommentListBO qo);
    /**
     * 获得子评论列表
     * @param qo
     * @return
     */
    Page<PostCommentVO> listPostCommentReply(QueryCommentReplyListBO qo);
    /**
     * 获得单条帖子评论信息
     * @param commentId
     * @return
     */
    PostCommentVO getPostCommentByCommentId(Long commentId);
    /**
     * 发表评论
     * @param postCommentBO
     * @return
     */
    Long publishPostComment(PostCommentBO postCommentBO);

    /**
     * 验证帖子评论是否存在且未被删除
     * @param commentId
     * @return
     */
    Boolean validatePostComment(Long commentId);
}
