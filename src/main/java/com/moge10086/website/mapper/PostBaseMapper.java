package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moge10086.website.domain.dto.post.PostEditDTO;
import com.moge10086.website.domain.model.PostBase;
import com.moge10086.website.domain.qo.QueryPostManageListBO;
import com.moge10086.website.domain.vo.post.BasePostEditVO;
import com.moge10086.website.domain.vo.post.PostManageVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author 22872
 */
public interface PostBaseMapper extends BaseMapper<PostBase> {
    /**
     * 获取对应帖子的作者id
     * @param postId
     * @return
     */
    @Select("select author_id from post_base where post_id = #{postId}")
    Long getAuthorIdByPostId(Long postId);

    /**
     * 获取对应帖子状态
     * @param postId
     * @return
     */
    @Select("select post_state from post_base where post_id = #{postId}")
    Integer getPostStateByPostId(Long postId);

    /**
     * 向数据库插入PostBase，返回postId
     * @param postBase
     * @return
     */
    Long insertPostBase(PostBase postBase);

    /**
     * 更新编辑后的帖子
     * @param postEditDTO
     */
    void updatePostBase(PostEditDTO postEditDTO);

    /**
     * 删除帖子（设置帖子状态为删除）
     * @param postId
     * @param postState
     * @param updateTime
     * @return
     */
    @Update("update post_base set post_state = #{postState},update_time = #{updateTime} where post_id=#{postId}")
    Boolean updatePostState(Long postId, Integer postState, Date updateTime);

    /**
     * 获得帖子编辑展示内容
     * @param postId
     * @return
     */
    BasePostEditVO getPostEditView(Long postId);

    /**
     * 获得用户帖子管理展示列表
     * @param page
     * @param queryPostManageListBO
     * @return
     */
    IPage<PostManageVO> listPostManageViews(IPage<PostManageVO> page,@Param("queryBO") QueryPostManageListBO queryPostManageListBO);
}
