/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.chargeoff;

/**
 * 添加核销记录
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffUpdateDTO extends ChargeOffDTO {

    /**
     * 核销记录id
     */
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
