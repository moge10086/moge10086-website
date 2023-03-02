package com.moge10086.website.service;

import com.moge10086.website.domain.bo.PostCommentBO;
import com.moge10086.website.domain.vo.comment.PostCommentVO;

/**
 * @author 22872
 */
public interface PostCommentService {
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
