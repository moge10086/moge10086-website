package com.moge10086.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moge10086.website.domain.bo.PostArticleBO;
import com.moge10086.website.domain.query.qo.post.QueryPostManageListBO;
import com.moge10086.website.domain.vo.post.ArticleEditVO;
import com.moge10086.website.domain.vo.post.BasePostVO;

/**
 * @author 22872
 */
public interface PostManageService {
    /**
     * 验证该用户能否操作该帖子以及该帖子能否被操作（锁定、删除则无法操作返回false）
     * @param userId
     * @param postId
     * @return Boolean
     */
    Boolean validateOperatePermissionByUserIdAndPostId(Long userId, Long postId);

    /**
     * 将文章帖子保存到数据库,返回postId
     * @param authorId
     * @param postArticleBO
     * @return
     */
    Long savePostArticle(Long authorId,PostArticleBO postArticleBO);

    /**
     * 编辑文章帖子
     *
     * @param authorId
     * @param postArticleBO
     */
    void editPostArticle(Long authorId,PostArticleBO postArticleBO);

    /**
     * 删除帖子，逻辑删除
     * @param postId
     * @return
     */
    Boolean deletePost(Long postId);

    /**
     * 发布帖子
     * @param postId
     * @return
     */
    Boolean publishPost(Long postId);

    /**
     * 撤销帖子的展示
     * @param postId
     * @return
     */
    Boolean cancelPost(Long postId);

    /**
     * 获取文章编辑内容
     * @param postId
     * @return
     */
    ArticleEditVO getArticleEditView(Long postId);

    /**
     * 返回用户帖子管理展示列表
     *
     * @param queryPostManageListBO
     * @return
     */
    Page<BasePostVO> getManagePostList(QueryPostManageListBO queryPostManageListBO);

    /**
     * 点赞帖子
     *
     * @param userId
     * @param postId
     * @return
     */
    Boolean praisePost(Long userId, Long postId);
    /**
     * 收藏帖子
     *
     * @param userId
     * @param postId
     * @return
     */
    Boolean favoritePost(Long userId, Long postId);
}
