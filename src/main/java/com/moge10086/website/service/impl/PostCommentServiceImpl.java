package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.bo.PostCommentBO;
import com.moge10086.website.domain.model.PostComment;
import com.moge10086.website.domain.query.qo.QueryCommentReplyListBO;
import com.moge10086.website.domain.query.qo.QueryPostCommentListBO;
import com.moge10086.website.domain.vo.comment.PostCommentVO;
import com.moge10086.website.domain.vo.comment.RootPostCommentVO;
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
    public Page<RootPostCommentVO> listRootPostComment(QueryPostCommentListBO qo) {
        //先查询根评论
        Page<RootPostCommentVO> rootPostCommentPage=Page.of(qo.getCurrentPage(), qo.getPageSize());
        postCommentMapper.listRootPostComments(rootPostCommentPage,qo.getPostId());
        //查询每条根评论下的回复(查询每个根评论id对应的前5条回复)
        rootPostCommentPage.getRecords().
                forEach(a->{
                    Page<PostCommentVO> childrenPostCommentPage=Page.of(1, 5);
                    a.setChildrenPostCommentList((Page<PostCommentVO>) postCommentMapper.listChildrenPostComments(childrenPostCommentPage,a.getSendCommentVO().getCommentId()));
                    a.getChildrenPostCommentList().getRecords().forEach(PostCommentVO::validateRepliedCommentByStatus);
                });
        return rootPostCommentPage;
    }
    @Override
    public Page<PostCommentVO> listPostCommentReply(QueryCommentReplyListBO qo) {
        Page<PostCommentVO> childrenPostCommentPage=Page.of(qo.getCurrentPage(), qo.getPageSize());
        postCommentMapper.listChildrenPostComments(childrenPostCommentPage,qo.getCommentId());
        childrenPostCommentPage.getRecords().forEach(PostCommentVO::validateRepliedCommentByStatus);
        return childrenPostCommentPage;
    }

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
            postCommentMapper.insertReplyComment(replyComment);
            return replyComment.getCommentId();
        }
    }

    @Override
    public Boolean validatePostComment(Long commentId) {
        Integer commentState=postCommentMapper.getCommentStateByCommentId(commentId);
        return commentState != null && commentState.equals(CommentState.SHOW.type);
    }
}
