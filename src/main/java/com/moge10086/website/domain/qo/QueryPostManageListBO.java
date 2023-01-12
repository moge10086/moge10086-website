package com.moge10086.website.domain.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author 22872
 */
public class QueryPostManageListBO extends BaseQueryPostListBO{
    @Schema(description ="作者ID",defaultValue = "1")
    @JsonIgnore
    private Long authorId;

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
                "} " + super.toString();
    }
}
