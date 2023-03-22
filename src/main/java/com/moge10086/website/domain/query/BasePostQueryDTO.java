package com.moge10086.website.domain.query;

/**
 * 查询对应条件帖子
 * @author 22872
 */
public class BasePostQueryDTO {
    private Integer postType;
    private Integer postState;
    private Integer sortType;
    private Boolean sortOrder;

    public BasePostQueryDTO() {
    }

    public BasePostQueryDTO(Integer postType, Integer postState, Integer sortType, Boolean sortOrder) {
        this.postType = postType;
        this.postState = postState;
        this.sortType = sortType;
        this.sortOrder = sortOrder;
    }

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

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Boolean getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Boolean sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "BasePostQueryDTO{" +
                "postType=" + postType +
                ", postState=" + postState +
                ", sortType=" + sortType +
                ", sortOrder=" + sortOrder +
                '}';
    }
}
