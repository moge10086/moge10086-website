package com.moge10086.website.service;

import com.moge10086.website.domain.bo.PostArticleBO;

/**
 * @author 22872
 */
public interface PostOptService {
    /**
     * 验证该用户能否操作该帖子以及该帖子能否被操作
     * @param userId
     * @param postId
     * @return Boolean
     */
    Boolean validatePermissionByUserIdAndPostId(Long userId,Long postId);

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
}
