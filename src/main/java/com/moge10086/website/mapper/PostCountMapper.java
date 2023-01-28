package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.model.PostCount;

import java.util.List;

/**
 * @author 22872
 */
public interface PostCountMapper extends BaseMapper<PostCount> {
    List<PostCount> listPostCount(List<Long> postIds);
    PostCount getPostCount(Long postId);
}
