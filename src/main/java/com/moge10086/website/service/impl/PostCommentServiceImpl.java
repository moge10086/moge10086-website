package com.moge10086.website.service.impl;

import com.moge10086.website.domain.bo.PostCommentBO;
import com.moge10086.website.domain.model.PostComment;
import com.moge10086.website.domain.vo.comment.PostCommentVO;
import com.moge10086.website.enums.CommentState;
import com.moge10086.website.mapper.PostCommentMapper;
import com.moge10086.website.service.PostCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 22872
 */
@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Resource
    PostCommentMapper postCommentMapper;

    @Override
    public PostCommentVO getPostCommentByCommentId(Long commentId) {
        PostCommentVO postCommentVO=postCommentMapper.getPostCommentByCommentId(commentId);
        if (postCommentVO!=null&&Objects.equals(postCommentVO.getRepliedCommentVO().getCommentState(), CommentState.DELETE.type)){
            postCommentVO.getRepliedCommentVO().setCommentContent("该评论已被删除");
        }
        return postCommentVO;
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public Long publishPostComment(PostCommentBO postCommentBO) {
        if (postCommentBO.getRepliedCommentId()==null){
            //发表根评论
            PostComment rootComment=PostComment.initRootComment(postCommentBO);
            postCommentMapper.insertRootComment(rootComment);
            postCommentMapper.updateRootAndReplied(rootComment.getCommentId());
            return rootComment.getCommentId();
        }else{
            //发表回复
            //获得根评论ID，帖子ID
            postCommentBO.setPostId(postCommentMapper.getPostIdByCommentId(postCommentBO.getRepliedCommentId()));
            Long rootCommentId=postCommentMapper.getRootCommentIdByCommentId(postCommentBO.getRepliedCommentId());
            PostComment replyComment=PostComment.initReplyComment(rootCommentId,postCommentBO);
            postCommentMapper.insertRootComment(replyComment);
            return replyComment.getCommentId();
        }
    }

    @Override
    public Boolean validatePostComment(Long commentId) {
        Integer commentState=postCommentMapper.getCommentStateByCommentId(commentId);
        return commentState != null && commentState.equals(CommentState.SHOW.type);
    }
}
