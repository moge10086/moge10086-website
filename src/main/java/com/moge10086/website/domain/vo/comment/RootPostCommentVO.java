package com.moge10086.website.domain.vo.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 22872
 */
public class RootPostCommentVO {
    SendUserVO sendUserVO;
    SendCommentVO sendCommentVO;
    Page<PostCommentVO> childrenPostCommentList;

    public SendUserVO getSendUserVO() {
        return sendUserVO;
    }

    public void setSendUserVO(SendUserVO sendUserVO) {
        this.sendUserVO = sendUserVO;
    }

    public SendCommentVO getSendCommentVO() {
        return sendCommentVO;
    }

    public void setSendCommentVO(SendCommentVO sendCommentVO) {
        this.sendCommentVO = sendCommentVO;
    }

    public Page<PostCommentVO> getChildrenPostCommentList() {
        return childrenPostCommentList;
    }

    public void setChildrenPostCommentList(Page<PostCommentVO> childrenPostCommentList) {
        this.childrenPostCommentList = childrenPostCommentList;
    }

    @Override
    public String toString() {
        return "RootPostCommentVO{" +
                "sendUserVO=" + sendUserVO +
                ", sendCommentVO=" + sendCommentVO +
                ", childrenPostCommentList=" + childrenPostCommentList +
                '}';
    }
}
