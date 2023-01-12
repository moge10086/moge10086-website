package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.dto.post.PostArticleEditDTO;
import com.moge10086.website.domain.model.PostArticle;
import org.apache.ibatis.annotations.Select;

/**
 * @author 22872
 */
public interface PostArticleMapper extends BaseMapper<PostArticle> {
    /**
     * 更新编辑后的文章
     * @param editPostArticleDTO
     */
    void updatePostArticle(PostArticleEditDTO editPostArticleDTO);

    /**
     * 获得文章内容
     * @param postId
     * @return
     */
    @Select("select article_content from post_article where post_id = #{postId}")
    String getArticleContent(Long postId);
}
