package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.bo.PostArticleBO;
import com.moge10086.website.domain.dto.post.PostArticleEditDTO;
import com.moge10086.website.domain.model.PostArticle;
import com.moge10086.website.domain.model.PostBase;
import com.moge10086.website.domain.model.PostCount;
import com.moge10086.website.domain.query.PostQueryDTO;
import com.moge10086.website.domain.query.qo.QueryPostManageListBO;
import com.moge10086.website.domain.vo.post.ArticleEditVO;
import com.moge10086.website.domain.vo.post.BasePostEditVO;
import com.moge10086.website.domain.vo.post.BasePostVO;
import com.moge10086.website.enums.PostState;
import com.moge10086.website.enums.PostType;
import com.moge10086.website.mapper.PostArticleMapper;
import com.moge10086.website.mapper.PostBaseMapper;
import com.moge10086.website.mapper.PostCountMapper;
import com.moge10086.website.mapper.PostQueryMapper;
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
        /* todo
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

}
