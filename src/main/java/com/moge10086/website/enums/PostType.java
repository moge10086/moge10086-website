package com.moge10086.website.enums;

/**
 * @author 邵权
 * @describe 帖子类别;文章、视频、交流等
 */
public enum PostType {
    //所有类型
    ALL_TYPE(0,"所有类型"),
    //文章 没有下载链接的分享
    ARTICLE(5,"文章"),
    //聊天 用来聊天,帖子没有文本只有评论
    CHAT(10, "交流"),
    //视频 带播放器的看视频的帖子
    VIDEO(15, "视频"),
    //公告 只有管理员可以发布的帖子
    NOTICE(20,"公告");

    public final Integer type;
    public final String value;

    PostType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
