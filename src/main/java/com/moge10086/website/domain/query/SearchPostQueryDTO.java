package com.moge10086.website.domain.query;

import com.moge10086.website.domain.query.qo.post.SearchPostCardListBO;
import com.moge10086.website.enums.PostState;
import org.springframework.beans.BeanUtils;

/**
 * @author 22872
 */
public class SearchPostQueryDTO extends BasePostQueryDTO{
    private String keywords;
    public static SearchPostQueryDTO generateQuery(SearchPostCardListBO searchPostCardListBO){
        SearchPostQueryDTO queryDTO=new SearchPostQueryDTO();
        BeanUtils.copyProperties(searchPostCardListBO,queryDTO);
        //只能查询被展示的帖子
        queryDTO.setPostState(PostState.SHOW.type);
        return queryDTO;
    }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "SearchPostQueryDTO{" +
                "keywords='" + keywords + '\'' +
                "} " + super.toString();
    }
}
