package com.moge10086.website.domain.vo.post;

/**
 * 帖子管理界面展示的信息
 * @author 22872
 */
public class PostManageVO extends BasePostVO {
    /** 帖子类别;文章、视频、交流等 */
    private Integer postType ;
    /** 帖子状态;草稿（0）、待审核（5）、上架（10）、下架（草稿）、封禁（15）、删除（-1） */
    private Integer postState ;

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Integer getPostState() {
        return postState;
    }

    public void setPostState(Integer postState) {
        this.postState = postState;
    }

    @Override
    public String toString() {
        return "PostManageVO{" +
                "postType=" + postType +
                ", postState=" + postState +
                "} " + super.toString();
    }
}
