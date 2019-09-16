/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 通用的返回值对象
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class Response<T> extends BaseObject {
    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}