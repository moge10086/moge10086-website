package com.moge10086.website.domain.vo.post;

import com.moge10086.website.domain.vo.user.BaseUserVO;

/**
 * 展示帖子信息
 *
 * @author 22872
 */
public class PostShowVO {

    BasePostVO basePostVO;
    BaseUserVO baseUserVO;
    Integer praiseState;
    Integer favoriteState;

    public PostShowVO() {
    }

    public PostShowVO(BasePostVO basePostVO, BaseUserVO baseUserVO, Integer praiseState, Integer favoriteState) {
        this.basePostVO = basePostVO;
        this.baseUserVO = baseUserVO;
        this.praiseState = praiseState == null ? 0 : praiseState;
        this.favoriteState = favoriteState == null ? 0 : favoriteState;
    }

    public BasePostVO getBasePostVO() {
        return basePostVO;
    }

    public void setBasePostVO(BasePostVO basePostVO) {
        this.basePostVO = basePostVO;
    }

    public BaseUserVO getBaseUserVO() {
        return baseUserVO;
    }

    public void setBaseUserVO(BaseUserVO baseUserVO) {
        this.baseUserVO = baseUserVO;
    }

    public Integer getPraiseState() {
        return praiseState;
    }

    public void setPraiseState(Integer praiseState) {
        this.praiseState = praiseState;
    }

    public Integer getFavoriteState() {
        return favoriteState;
    }

    public void setFavoriteState(Integer favoriteState) {
        this.favoriteState = favoriteState;
    }

    @Override
    public String toString() {
        return "PostShowVO{" +
                "basePostVO=" + basePostVO +
                ", baseUserVO=" + baseUserVO +
                ", praiseState=" + praiseState +
                ", favoriteState=" + favoriteState +
                '}';
    }
}
