/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 核销状态enum
 *
 * @author niexiao
 * @created 2020-03-04
 */
public enum ChargeOffStatusEnum {

    UNCOMMITTED(0, "未提交"),
    COMMITTED(1, "已提交");

    private static Map<Integer, ChargeOffStatusEnum> codeMap = Maps.newHashMap();

    static {
        for (ChargeOffStatusEnum type : ChargeOffStatusEnum.values()) {
            codeMap.put(type.getCode(), type);
        }
    }


    private Integer code;
    private String name;

    ChargeOffStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ChargeOffStatusEnum getByCode(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
