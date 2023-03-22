package com.moge10086.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.query.qo.post.QueryFavoritePostCardListBO;
import com.moge10086.website.domain.query.qo.post.QueryPostCardListBO;
import com.moge10086.website.domain.query.qo.post.QueryUserPostCardListBO;
import com.moge10086.website.domain.vo.post.ArticleShowVO;
import com.moge10086.website.domain.vo.post.PostCardVO;
import com.moge10086.website.domain.vo.post.PostShowVO;

/**
 * @author 22872
 */
public interface PostShowService {
    /**
     * 验证该帖子能否展示给对应用户
     * @param userId
     * @param postId
     * @return
     */
    Boolean validateShowPermissionByUserIdAndPostId(Long userId, Long postId);
    /**
     * 返回帖子卡片信息列表
     * @param queryPostCardListBO
     * @return
     */
    Page<PostCardVO> listPostCards(QueryPostCardListBO queryPostCardListBO);
    /**
     * 返回对应用户作品帖子卡片信息列表
     * @param queryUserPostCardListBO
     * @return
     */
    Page<PostCardVO> listUserPostCards(QueryUserPostCardListBO queryUserPostCardListBO);
    /**
     * 返回用户收藏帖子卡片信息列表
     * @param queryFavoritePostCardListBO
     * @return
     */
    Page<PostCardVO> listFavoritePostCards(QueryFavoritePostCardListBO queryFavoritePostCardListBO);

    /**
     * 返回文章展示信息
     * @param userId
     * @param postId
     * @return
     */
    ArticleShowVO getArticleShow(Long userId, Long postId);

    /**
     * 返回帖子展示信息
     * @param userId
     * @param postId
     * @return
     */
    PostShowVO getPostShow(Long userId,Long postId);
}
