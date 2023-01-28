package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moge10086.website.domain.dto.PostQueryDTO;
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


}
