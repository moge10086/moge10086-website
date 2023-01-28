package com.moge10086.website.enums;

/**
 * @author legion
 * @describe 按什么规则排序
 */
public enum SortType {
    //按更新时间排序
    TIME(1, "按更新时间排序"),
    //点赞数排序
    PRAISE(5, "按点赞数排序"),
    //浏览量排序
    READ(10, "按浏览量排序"),
    //收藏数排序
    FAVORITE(15, "按收藏数排序"),
    //评论数排序
    COMMENT(20, "按评论数排序");
    public final Integer type;
    public final String value;

    SortType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
    public static SortType getEnumByType(int type){
        for (SortType sortType : values()) {
            if (sortType.type == type) {
                return sortType;
            }
        }
        return null;
    }
    public static Boolean isInclude(int type){
        for (SortType sortType : values()) {
            if (sortType.type == type) {
                return true;
            }
        }
        return false;
    }
}
