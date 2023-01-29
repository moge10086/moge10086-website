package com.moge10086.website.domain.query.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moge10086.website.enums.PostState;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * @author 22872
 */
public class QueryPostManageListBO extends BaseQueryPostListBO{
    @Schema(description ="作者ID",defaultValue = "1")
    @JsonIgnore
    private Long authorId;

    @Schema(description ="帖子状态",defaultValue = "0")
    @NotNull(message = "postState不能为空")
    private Integer postState;

    @JsonIgnore
    @Override
    public Boolean isValid(){
        //传入的值不在枚举类中
        if(!PostState.isInclude(postState)){
            return false;
        }
        //已被删除的帖子不可查询
        if (postState.equals(PostState.DELETE.type)){
            return false;
        }
        return super.isValid();
    }
    public Integer getPostState() {
        return postState;
    }

    public void setPostState(Integer postState) {
        this.postState = postState;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "QueryPostManageListBO{" +
                "authorId=" + authorId +
                ", postState=" + postState +
                "} " + super.toString();
    }
}
