package com.moge10086.website.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子热度表;
 * @author : 邵权
 * @date : 2023-4-23
 */
public class PostHeat implements Serializable,Cloneable{
    /** 帖子ID */
    private Long postId ;
    /** 热度 */
    private Double heat ;
    /** 最后更新数据 */
    private Date updateTime ;

    /** 帖子ID */
    public Long getPostId(){
        return this.postId;
    }
    /** 帖子ID */
    public void setPostId(Long postId){
        this.postId=postId;
    }

    public Double getHeat() {
        return heat;
    }

    public void setHeat(Double heat) {
        this.heat = heat;
    }

    /** 最后更新数据 */
    public Date getUpdateTime(){
        return this.updateTime;
    }
    /** 最后更新数据 */
    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }
}