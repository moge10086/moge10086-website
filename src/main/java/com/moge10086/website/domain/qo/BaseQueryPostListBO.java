package com.moge10086.website.domain.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moge10086.website.enums.PostState;
import com.moge10086.website.enums.PostType;
import com.moge10086.website.enums.SortOrder;
import com.moge10086.website.enums.SortType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 22872
 */
public class BaseQueryPostListBO {
    /**
     * 分页返回上限
     */
    public static final Integer PAGE_SIZE_LIMIT =60;
    @Schema(description ="帖子类别",defaultValue = "0")
    @NotNull(message = "postType不能为空")
    private Integer postType;
    @Schema(description ="帖子状态",defaultValue = "0")
    @NotNull(message = "postState不能为空")
    private Integer postState;
    @Schema(description ="排序类别:名称、创建时间、更新时间",defaultValue = "1")
    @NotNull(message = "sortType不能为空")
    private Integer sortType;
    @Schema(description ="排序顺序:增序、降序",defaultValue = "false")
    @NotNull(message = "sortOrder不能为空")
    private Boolean sortOrder;
    @Schema(description ="分页大小:最大60",defaultValue = "30")
    @Min(value = 1,message = "分页大小在1到60间")
    @Max(value = 60,message = "分页大小在5到60间")
    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    @Schema(description ="当前页数",defaultValue = "1")
    @Min(value = 1,message = "当前页数大于1")
    @NotNull(message = "currentPage不能为空")
    private Integer currentPage;

    /**
     * 严格校验参数的值，判断是否合法
     */
    @JsonIgnore
    public Boolean isValid(){
        //传入的值不在枚举类中
        if(!PostType.isInclude(postType)||!PostState.isInclude(postState)||!SortType.isInclude(sortType)||!SortOrder.isInclude(sortOrder)){
            return false;
        }
        //已被删除的帖子不可查询
        if (postState.equals(PostState.DELETE.type)){
            return false;
        }
        return true;
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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "BaseQueryPostListBO{" +
                "postType=" + postType +
                ", postState=" + postState +
                ", sortType=" + sortType +
                ", sortOrder=" + sortOrder +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}