package com.moge10086.website.enums;

/**
 * @author legion
 * @describe 排序方式
 */
public enum SortOrder {
    //从高到低,从新到旧
    DESC(false, "降序"),
    //从低到高,从新到旧
    ASC(true, "增序");

    public final Boolean type;
    public final String value;

    SortOrder(Boolean type, String value) {
        this.type = type;
        this.value = value;
    }
    public static SortOrder getEnumByType(boolean type){
        for (SortOrder sortOrder : values()) {
            if (sortOrder.type == type) {
                return sortOrder;
            }
        }
        return null;
    }
    public static Boolean isInclude(boolean type){
        for (SortOrder sortOrder : values()) {
            if (sortOrder.type == type) {
                return true;
            }
        }
        return false;
    }
}
