package com.moge10086.website.enums;

/**
 * @author 邵权
 * @describe 帖子状态 草稿（0）、待审核（5）、展示（10）、下架（草稿）、锁定（15）、删除（-1）
 */
public enum PostStatus {
    //草稿
    DRAFT(0, "草稿"),
    //审核中
    //原本是5,现在是发布即展示状态
    REVIEWING(5, "待审核"),
    //展示
    SHOW(10, "展示"),
    //锁定
    LOCK(15, "锁定"),
    //删除
    DELETE(-1,"删除");

    public final Integer type;
    public final String value;

    PostStatus(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
