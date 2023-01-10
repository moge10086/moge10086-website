package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.dto.post.EditPostArticleDTO;
import com.moge10086.website.domain.model.PostArticle;

/**
 * @author 22872
 */
public interface PostArticleMapper extends BaseMapper<PostArticle> {
    /**
     * 更新编辑后的文章
     * @param editPostArticleDTO
     */
    void updatePostArticle(EditPostArticleDTO editPostArticleDTO);
}
