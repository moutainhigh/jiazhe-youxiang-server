package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description 登陆
 * @date 2018/11/5.
 */
public enum LoginType {

    USER("user"), CUSTOMER("customer");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
