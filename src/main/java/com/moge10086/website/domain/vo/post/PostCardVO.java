package com.moge10086.website.domain.vo.post;

import com.moge10086.website.domain.vo.user.BaseUserVO;

/**
 * @author sq
 * 帖子卡片展示所需信息
 */
public class PostCardVO{
    private BasePostVO basePostVO;
    private BaseUserVO baseUserVO;

    public PostCardVO(BasePostVO basePostVO, BaseUserVO baseUserVO) {
        this.basePostVO = basePostVO;
        this.baseUserVO = baseUserVO;
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

    @Override
    public String toString() {
        return "PostCardVO{" +
                "basePostVO=" + basePostVO +
                ", baseUserVO=" + baseUserVO +
                "} " + super.toString();
    }
}
