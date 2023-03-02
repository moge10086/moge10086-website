package com.moge10086.website.enums;

public enum CommentState {
    //
    DELETE(-1,"删除"),
    //
    SHOW(1, "展示");
    public final Integer type;
    public final String value;

    CommentState(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
