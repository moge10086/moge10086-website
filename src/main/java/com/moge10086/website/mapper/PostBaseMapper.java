package com.moge10086.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moge10086.website.domain.dto.post.PostEditDTO;
import com.moge10086.website.domain.model.PostBase;
import com.moge10086.website.domain.model.PostHeat;
import com.moge10086.website.domain.vo.post.BasePostEditVO;
import com.moge10086.website.domain.vo.post.BasePostVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

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
     * 设置帖子状态
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
    //todo 临时使用，获得最近一周的帖子
    List<BasePostVO> listPostBase();
    //todo 临时使用，插入/更新热度
    int insertHeat(List<PostHeat> postHeats);
}
