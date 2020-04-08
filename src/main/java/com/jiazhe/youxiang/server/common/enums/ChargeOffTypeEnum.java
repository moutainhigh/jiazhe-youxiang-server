/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 核销兑换类型enum
 *
 * @author niexiao
 * @created 2020-03-04
 */
public enum ChargeOffTypeEnum {

    PRODUCT(0, "兑换商品"),
    POINT(1, "充值积分");

    private static Map<Integer, ChargeOffTypeEnum> codeMap = Maps.newHashMap();

    static {
        for (ChargeOffTypeEnum type : ChargeOffTypeEnum.values()) {
            codeMap.put(type.getCode(), type);
        }
    }


    private Integer code;
    private String name;

    ChargeOffTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ChargeOffTypeEnum getByCode(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
