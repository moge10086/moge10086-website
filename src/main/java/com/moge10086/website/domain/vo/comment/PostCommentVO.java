package com.moge10086.website.domain.vo.comment;

import com.moge10086.website.enums.CommentState;

/**
 * @author 22872
 */
public class PostCommentVO {
    SendUserVO sendUserVO;
    SendCommentVO sendCommentVO;
    RepliedUserVO repliedUserVO;
    RepliedCommentVO repliedCommentVO;
    public void validateRepliedCommentByStatus(){
        if(!this.repliedCommentVO.getCommentState().equals(CommentState.SHOW.type)) {
            this.repliedCommentVO.setCommentContent("该评论已被删除");
        }
    }
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

    public RepliedUserVO getRepliedUserVO() {
        return repliedUserVO;
    }

    public void setRepliedUserVO(RepliedUserVO repliedUserVO) {
        this.repliedUserVO = repliedUserVO;
    }

    public RepliedCommentVO getRepliedCommentVO() {
        return repliedCommentVO;
    }

    public void setRepliedCommentVO(RepliedCommentVO repliedCommentVO) {
        this.repliedCommentVO = repliedCommentVO;
    }

    @Override
    public String toString() {
        return "PostCommentVO{" +
                "sendUserVO=" + sendUserVO +
                ", sendCommentVO=" + sendCommentVO +
                ", repliedUserVO=" + repliedUserVO +
                ", repliedCommentVO=" + repliedCommentVO +
                '}';
    }
}
