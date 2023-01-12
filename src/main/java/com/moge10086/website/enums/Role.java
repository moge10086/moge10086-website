package com.moge10086.website.enums;

/**
 * @author legion
 * @describe 角色枚举类
 */
public enum Role{
    //站长
    ADMINISTRATOR(100, "站长"),
    //管理员
    MANAGER(20,"管理员"),
    //特殊用户
    SUPER_USER(15,"特殊用户"),
    //高级用户
    SENIOR_USER(10,"高级用户"),
    //普通用户
    NORMAL_USER(5, "普通用户"),
    //游客
    TOURIST(1,"游客");

    public final Integer type;
    public final String value;

    Role(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static Role getEnumByType(int type){
        for (Role role : values()) {
            if (role.type == type) {
                return role;
            }
        }
        return Role.TOURIST;
    }
    public static Boolean isInclude(int type){
        for (Role role : values()) {
            if (role.type == type) {
                return true;
            }
        }
        return false;
    }
}
