package com.moge10086.website.enums;

/**
 * @author legion
 * @describe 按什么规则排序
 */
public enum SortType {
    //按发布时间排序
    TIME(1, "按发布时间排序","update_time");
    public final Integer type;
    public final String value;
    public final String filedName;

    SortType(Integer type, String value,String filedName) {
        this.type = type;
        this.value = value;
        this.filedName =filedName;
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
