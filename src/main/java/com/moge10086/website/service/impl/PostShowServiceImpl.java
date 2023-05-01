package com.moge10086.website.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.model.PostBase;
import com.moge10086.website.domain.model.PostCount;
import com.moge10086.website.domain.query.FavoritePostQueryDTO;
import com.moge10086.website.domain.query.PostQueryDTO;
import com.moge10086.website.domain.query.SearchPostQueryDTO;
import com.moge10086.website.domain.query.qo.post.QueryFavoritePostCardListBO;
import com.moge10086.website.domain.query.qo.post.QueryPostCardListBO;
import com.moge10086.website.domain.query.qo.post.QueryUserPostCardListBO;
import com.moge10086.website.domain.query.qo.post.SearchPostCardListBO;
import com.moge10086.website.domain.vo.post.ArticleShowVO;
import com.moge10086.website.domain.vo.post.BasePostVO;
import com.moge10086.website.domain.vo.post.PostCardVO;
import com.moge10086.website.domain.vo.post.PostShowVO;
import com.moge10086.website.domain.vo.user.BaseUserVO;
import com.moge10086.website.enums.PostState;
import com.moge10086.website.mapper.*;
import com.moge10086.website.service.PostShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 22872
 */
@Service
public class PostShowServiceImpl implements PostShowService {
    @Resource
    PostBaseMapper postBaseMapper;
    @Resource
    PostArticleMapper postArticleMapper;
    @Resource
    PostCountMapper postCountMapper;
    @Resource
    PostQueryMapper postQueryMapper;
    @Resource
    UserQueryMapper userQueryMapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    PostPraiseMapper postPraiseMapper;
    @Resource
    PostFavoriteMapper postFavoriteMapper;

    @Override
    public Boolean validateShowPermissionByUserIdAndPostId(Long userId, Long postId) {
        //帖子存在且为展示状态
        Integer postState = postBaseMapper.getPostStateByPostId(postId);
        if (postState!=null&&postState.equals(PostState.SHOW.type)){
            return true;
        }
//        //用户是是作者，则不为删除状态
//        Long authorId = postBaseMapper.getAuthorIdByPostId(postId);
//        if (postState!=null&&userId.equals(authorId)&&!postState.equals(PostState.DELETE.type)){
//            return true;
//        }
        return false;
    }

    @Override
    public Page<PostCardVO> listPostCards(QueryPostCardListBO queryPostCardListBO) {
        //获得帖子ID及帖子基本信息，BasePostVO
        Page<BasePostVO> basePostPage=Page.of(queryPostCardListBO.getCurrentPage(), queryPostCardListBO.getPageSize());
        postQueryMapper.listBasePosts(basePostPage, PostQueryDTO.generateQuery(queryPostCardListBO));
        //根据帖子ID查询作者信息，BaseUserVO
        List<Long> authorIds = basePostPage.getRecords().stream().map(BasePostVO::getAuthorId).toList();
        Map<Long, BaseUserVO> baseUserMap = userQueryMapper.getBaseUsers(authorIds);
        //转换为PostCardVO
        return (Page<PostCardVO>)basePostPage.convert(n-> new PostCardVO(n,baseUserMap.get(n.getAuthorId())));
    }

    @Override
    public Page<PostCardVO> searchPostCards(SearchPostCardListBO searchPostCardListBO) {
        //获得帖子ID及帖子基本信息，BasePostVO
        Page<BasePostVO> basePostPage=Page.of(searchPostCardListBO.getCurrentPage(), searchPostCardListBO.getPageSize());
        postQueryMapper.searchBasePosts(basePostPage, SearchPostQueryDTO.generateQuery(searchPostCardListBO));
        //根据帖子ID查询作者信息，BaseUserVO
        List<Long> authorIds = basePostPage.getRecords().stream().map(BasePostVO::getAuthorId).toList();
        Map<Long, BaseUserVO> baseUserMap = userQueryMapper.getBaseUsers(authorIds);
        //转换为PostCardVO
        return (Page<PostCardVO>)basePostPage.convert(n-> new PostCardVO(n,baseUserMap.get(n.getAuthorId())));
    }

    @Override
    public Page<PostCardVO> listUserPostCards(QueryUserPostCardListBO queryUserPostCardListBO) {
        //获得帖子ID及帖子基本信息，BasePostVO
        Page<BasePostVO> basePostPage=Page.of(queryUserPostCardListBO.getCurrentPage(), queryUserPostCardListBO.getPageSize());
        postQueryMapper.listBasePosts(basePostPage, PostQueryDTO.generateQuery(queryUserPostCardListBO));
        // 因为是个人作品，页面已经带有作者信息所以不查询对应作者信息
        return (Page<PostCardVO>)basePostPage.convert(n-> new PostCardVO(n,null));
    }

    @Override
    public Page<PostCardVO> listFavoritePostCards(QueryFavoritePostCardListBO queryFavoritePostCardListBO) {
        //获得帖子ID及帖子基本信息，BasePostVO
        Page<BasePostVO> basePostPage=Page.of(queryFavoritePostCardListBO.getCurrentPage(), queryFavoritePostCardListBO.getPageSize());
        postQueryMapper.listFavoriteBasePosts(basePostPage, FavoritePostQueryDTO.generateQuery(queryFavoritePostCardListBO));
        //根据帖子ID查询作者信息，BaseUserVO
        List<Long> authorIds = basePostPage.getRecords().stream().map(BasePostVO::getAuthorId).toList();
        Map<Long, BaseUserVO> baseUserMap = userQueryMapper.getBaseUsers(authorIds);
        //转换为PostCardVO
        return (Page<PostCardVO>)basePostPage.convert(n-> new PostCardVO(n,baseUserMap.get(n.getAuthorId())));
    }

    @Override
    public ArticleShowVO getArticleShow(Long userId, Long postId) {
        return new ArticleShowVO(
                getPostShow(userId, postId),
                postArticleMapper.getArticleContent(postId));
    }

    @Override
    public PostShowVO getPostShow(Long userId, Long postId) {
        BasePostVO basePostVO=new BasePostVO();
        //获得帖子信息,post_base
        PostBase postBase = postBaseMapper.selectById(postId);
        BeanUtils.copyProperties(postBase,basePostVO);
        //获得帖子计数信息,post_count
        PostCount postCount = postCountMapper.selectById(postId);
        BeanUtils.copyProperties(postCount,basePostVO);
        //获得作者信息,user_info
        BaseUserVO baseUserVO = userInfoMapper.getUserVO(postBase.getAuthorId());
        //获得点赞信息,post_praise,可能为null
        Integer praiseState = postPraiseMapper.getPraiseState(userId, postId);
        Integer favoriteState = postFavoriteMapper.getFavoriteState(userId, postId);
        //浏览量+1
        postCountMapper.readCountPlusOne(postId);
        return new PostShowVO(basePostVO,baseUserVO,praiseState,favoriteState);
    }

    @Override
    public Page<PostCardVO> listHotPostCards(QueryPostCardListBO queryPostCardListBO) {
        //获得帖子ID及帖子基本信息，BasePostVO
        Page<BasePostVO> basePostPage=Page.of(queryPostCardListBO.getCurrentPage(), queryPostCardListBO.getPageSize());
        postQueryMapper.listHotPosts(basePostPage);
        //根据帖子ID查询作者信息，BaseUserVO
        List<Long> authorIds = basePostPage.getRecords().stream().map(BasePostVO::getAuthorId).toList();
        Map<Long, BaseUserVO> baseUserMap = userQueryMapper.getBaseUsers(authorIds);
        //转换为PostCardVO
        return (Page<PostCardVO>)basePostPage.convert(n-> new PostCardVO(n,baseUserMap.get(n.getAuthorId())));
    }
}
