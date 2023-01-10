package com.moge10086.website.service.impl;

import com.moge10086.website.domain.bo.PostArticleBO;
import com.moge10086.website.domain.dto.post.EditPostArticleDTO;
import com.moge10086.website.domain.model.PostArticle;
import com.moge10086.website.domain.model.PostBase;
import com.moge10086.website.enums.PostStatus;
import com.moge10086.website.enums.PostType;
import com.moge10086.website.mapper.PostArticleMapper;
import com.moge10086.website.mapper.PostBaseMapper;
import com.moge10086.website.service.PostOptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 22872
 */
@Service
public class PostOptServiceImpl implements PostOptService {
    @Resource
    PostBaseMapper postBaseMapper;
    @Resource
    PostArticleMapper postArticleMapper;
    @Override
    public Boolean validatePermissionByUserIdAndPostId(Long userId,Long postId) {
        Long authorId = postBaseMapper.getAuthorIdByPostId(postId);
        if (!userId.equals(authorId)){
            //帖子不存在或者authorId与userId不对应
            return false;
        }
        Integer postState= postBaseMapper.getPostStateByPostId(postId);
        //如果帖子状态为锁定或删除则不可操作
        return !postState.equals(PostStatus.DELETE.type) && !postState.equals(PostStatus.LOCK.type);
    }

    @Override
    @Transactional
    public Long savePostArticle(Long authorId,PostArticleBO postArticleBO) {
        //分别对PostArticle，PostBase操作
        //生成并插入postBase
        PostBase postBase=PostBase.initPostBase(authorId, PostType.ARTICLE.type,postArticleBO);
        postBaseMapper.insertPostBase(postBase);
        //生成并插入PostArticle
        PostArticle postArticle = PostArticle.initPostArticle(postBase.getPostId(),postArticleBO);
        postArticleMapper.insert(postArticle);
        return postBase.getPostId();
    }

    @Override
    public void editPostArticle(Long authorId, PostArticleBO postArticleBO) {
        //更新title、summary、coverImg、articleContent、updateTime
        //生成文章更新实体
        EditPostArticleDTO editPostArticleDTO=EditPostArticleDTO.initEditPostArticleDTO(postArticleBO);
        //更新postBase
        postBaseMapper.updatePostBase(editPostArticleDTO);
        //更新PostArticle：articleContent
        postArticleMapper.updatePostArticle(editPostArticleDTO);
    }

    @Override
    public Boolean deletePost(Long postId) {
        return postBaseMapper.updatePostState(postId,PostStatus.DELETE.type,new Date());
    }

    @Override
    public Boolean publishPost(Long postId) {
        //todo 代管理员功能完成后改为审核状态
        return postBaseMapper.updatePostState(postId,PostStatus.SHOW.type,new Date());
    }

}
