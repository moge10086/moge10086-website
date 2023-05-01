package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moge10086.website.domain.query.FavoritePostQueryDTO;
import com.moge10086.website.domain.query.PostQueryDTO;
import com.moge10086.website.domain.query.SearchPostQueryDTO;
import com.moge10086.website.domain.vo.post.BasePostVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author 22872
 */
public interface PostQueryMapper {
    /**
     * 根据查询条件返回帖子ID
     * @param page
     * @param postQueryDTO
     * @return
     */
    IPage<Long> listPostIds(IPage<Long> page, @Param("query") PostQueryDTO postQueryDTO);
    /**
     * 根据查询条件返回帖子基本信息
     * @param page
     * @param postQueryDTO
     * @return
     */
    IPage<BasePostVO> listBasePosts(IPage<BasePostVO> page, @Param("query") PostQueryDTO postQueryDTO);
    /**
     * 返回用户被收藏的帖子列表信息（用户id）
     * @param page
     * @param favoritePostQueryDTO
     * @return
     */
    IPage<BasePostVO> listFavoriteBasePosts(IPage<BasePostVO> page, @Param("query") FavoritePostQueryDTO favoritePostQueryDTO);
    /**
     * 关键词全文检索帖子列表信息
     * @param page
     * @param searchPostQueryDTO
     * @return
     */
    IPage<BasePostVO> searchBasePosts(IPage<BasePostVO> page, @Param("query") SearchPostQueryDTO searchPostQueryDTO);

    //todo 临时使用
    IPage<BasePostVO> listHotPosts(IPage<BasePostVO> page);
}
