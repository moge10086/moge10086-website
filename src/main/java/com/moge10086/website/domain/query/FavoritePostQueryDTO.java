package com.moge10086.website.domain.query;

import com.moge10086.website.domain.query.qo.post.QueryFavoritePostCardListBO;
import com.moge10086.website.enums.PostState;
import org.springframework.beans.BeanUtils;

/**
 * 查询用户收藏文章
 * @author 22872
 */
public class FavoritePostQueryDTO extends BasePostQueryDTO {
    private Long userId;

    public static FavoritePostQueryDTO generateQuery(QueryFavoritePostCardListBO queryFavoritePostCardListBO){
        FavoritePostQueryDTO queryDTO=new FavoritePostQueryDTO();
        BeanUtils.copyProperties(queryFavoritePostCardListBO,queryDTO);
        //只能查询被展示的帖子
        queryDTO.setPostState(PostState.SHOW.type);
        return queryDTO;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FavoritePostQueryDTO{" +
                "userId=" + userId +
                "} " + super.toString();
    }
}
