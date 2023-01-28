package com.moge10086.website.domain.dto;

import com.moge10086.website.domain.qo.QueryPostManageListBO;
import com.moge10086.website.enums.SortOrder;
import com.moge10086.website.enums.SortType;
import org.springframework.beans.BeanUtils;

/**
 * @author sq
 * 设置所有的帖子查询条件
 */
public class PostQueryDTO {
    private Integer postType;
    private Integer postState;
    private Integer sortType;
    private Boolean sortOrder;
    private Long authorId;

    public PostQueryDTO() {
    }

    /**
     * 生成查询实体
     * @param postType
     * @param postState
     * @param sortType
     * @param sortOrder
     * @param authorId
     * @return
     */
    public static PostQueryDTO generateQuery(Integer postType, Integer postState, Integer sortType, Boolean sortOrder, Long authorId){
        return new PostQueryDTO(
                postType,
                postState,
                sortType==null?SortType.TIME.type:sortType,
                sortOrder==null?SortOrder.DESC.type:sortOrder,
                authorId
        );
    }
    public static PostQueryDTO generateQuery(QueryPostManageListBO queryPostManageListBO){
        PostQueryDTO queryDTO=new PostQueryDTO();
        BeanUtils.copyProperties(queryPostManageListBO,queryDTO);
        return queryDTO;
    }
    public PostQueryDTO(Integer postType, Integer postState, Integer sortType, Boolean sortOrder, Long authorId) {
        this.postType = postType;
        this.postState = postState;
        this.sortType = sortType;
        this.sortOrder = sortOrder;
        this.authorId = authorId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "PostQueryDTO{" +
                "postType=" + postType +
                ", postState=" + postState +
                ", sortType=" + sortType +
                ", sortOrder=" + sortOrder +
                ", authorId=" + authorId +
                '}';
    }
}
