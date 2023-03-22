package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.bo.PostArticleBO;
import com.moge10086.website.domain.dto.post.PostArticleEditDTO;
import com.moge10086.website.domain.model.*;
import com.moge10086.website.domain.query.PostQueryDTO;
import com.moge10086.website.domain.query.qo.post.QueryPostManageListBO;
import com.moge10086.website.domain.vo.post.ArticleEditVO;
import com.moge10086.website.domain.vo.post.BasePostEditVO;
import com.moge10086.website.domain.vo.post.BasePostVO;
import com.moge10086.website.enums.PostState;
import com.moge10086.website.enums.PostType;
import com.moge10086.website.mapper.*;
import com.moge10086.website.service.PostManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 22872
 */
@Service
public class PostManageServiceImpl implements PostManageService {
    @Resource
    PostBaseMapper postBaseMapper;
    @Resource
    PostArticleMapper postArticleMapper;
    @Resource
    PostCountMapper postCountMapper;
    @Resource
    PostQueryMapper postQueryMapper;
    @Resource
    PostPraiseMapper postPraiseMapper;
    @Resource
    PostFavoriteMapper postFavoriteMapper;
    @Override
    public Boolean validateOperatePermissionByUserIdAndPostId(Long userId, Long postId) {
        Long authorId = postBaseMapper.getAuthorIdByPostId(postId);
        if (!userId.equals(authorId)){
            //帖子不存在或者authorId与userId不对应
            return false;
        }
        Integer postState= postBaseMapper.getPostStateByPostId(postId);
        //如果帖子状态为锁定或删除则不可操作 todo 锁定功能代考虑，目前当锁定不存在
        return !postState.equals(PostState.DELETE.type) && !postState.equals(PostState.LOCK.type);
    }

    @Override
    @Transactional
    public Long savePostArticle(Long authorId,PostArticleBO postArticleBO) {
        /*
         * 同时生成post_base,post_count,post_article
         * 注意事务
         */
        //分别对PostArticle，PostBase操作
        //生成并插入postBase
        PostBase postBase=PostBase.initPostBase(authorId, PostType.ARTICLE.type,postArticleBO);
        postBaseMapper.insertPostBase(postBase);
        //生成并插入postCount
        PostCount postCount=PostCount.initPostCount(postBase.getPostId());
        postCountMapper.insert(postCount);
        //生成并插入PostArticle
        PostArticle postArticle = PostArticle.initPostArticle(postBase.getPostId(),postArticleBO);
        postArticleMapper.insert(postArticle);
        return postBase.getPostId();
    }

    @Override
    public void editPostArticle(Long authorId, PostArticleBO postArticleBO) {
        //更新title、summary、coverImg、articleContent、updateTime
        //生成文章更新实体
        PostArticleEditDTO editPostArticleDTO= PostArticleEditDTO.initEditPostArticleDTO(postArticleBO);
        //更新postBase
        postBaseMapper.updatePostBase(editPostArticleDTO);
        //更新PostArticle：articleContent
        postArticleMapper.updatePostArticle(editPostArticleDTO);
    }

    @Override
    public Boolean deletePost(Long postId) {
        return postBaseMapper.updatePostState(postId, PostState.DELETE.type,new Date());
    }

    @Override
    public Boolean publishPost(Long postId) {
        //todo 代管理员功能完成后改为审核状态
        return postBaseMapper.updatePostState(postId, PostState.SHOW.type,new Date());
    }

    @Override
    public Boolean cancelPost(Long postId) {
        return postBaseMapper.updatePostState(postId, PostState.DRAFT.type,new Date());
    }

    @Override
    public ArticleEditVO getArticleEditView(Long postId) {
        BasePostEditVO basePostEditVO =postBaseMapper.getPostEditView(postId);
        ArticleEditVO articleEditVO = ArticleEditVO.initFromBase(basePostEditVO);
        articleEditVO.setArticleContent(postArticleMapper.getArticleContent(postId));
        return articleEditVO;
    }

    @Override
    public Page<BasePostVO> getManagePostList(QueryPostManageListBO queryPostManageListBO) {
        //获得帖子ID及帖子基本信息
        Page<BasePostVO> basePostPage=Page.of(queryPostManageListBO.getCurrentPage(),queryPostManageListBO.getPageSize());
        postQueryMapper.listBasePosts(basePostPage,PostQueryDTO.generateQuery(queryPostManageListBO));
        return basePostPage;
    }

    @Override
    @Transactional
    public Boolean praisePost(Long userId, Long postId) {
        //查询点赞记录
        Integer originalPraiseState=postPraiseMapper.getPraiseState(userId,postId);
        if (originalPraiseState==null){
            //插入点赞记录，第一次都是点赞状态
            postPraiseMapper.insert(PostPraise.initPostPraise(postId,userId));
            postCountMapper.praiseCountPlusOne(postId);
        }else{
            //如果之前是点赞状态（1、true），则更新为取消状态（0、false）
            boolean praiseState=originalPraiseState==0;
            postPraiseMapper.updatePraiseState(userId,postId,praiseState?1:0,new Date());
            if (praiseState){
                postCountMapper.praiseCountPlusOne(postId);
            }else{
                postCountMapper.praiseCountMinusOne(postId);
            }
            return praiseState;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean favoritePost(Long userId, Long postId) {
        Integer originalFavoriteState=postFavoriteMapper.getFavoriteState(userId,postId);
        if (originalFavoriteState==null){
            //插入收藏记录，第一次都是收藏状态
            postFavoriteMapper.insert(PostFavorite.initPostFavorite(postId,userId));
            postCountMapper.favoriteCountPlusOne(postId);
        }else{
            //如果之前是收藏状态（1、true），则更新为取消状态（0、false）
            boolean favoriteState=originalFavoriteState==0;
            postFavoriteMapper.updateFavoriteState(userId,postId,favoriteState?1:0,new Date());
            if (favoriteState){
                postCountMapper.favoriteCountPlusOne(postId);
            }else{
                postCountMapper.favoriteCountMinusOne(postId);
            }
            return favoriteState;
        }
        return true;
    }

}
