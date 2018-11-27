/*
 * Copyright (c) 2017 maoyan.com
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
        Class c = data.getClass();
        System.out.println(c.getName());
        Field[] fields = c.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        for(Field field:fields){
            field.setAccessible(true);
            System.out.println(field.getName());
            System.out.println(field.getType().getName());
            if(field.getType().getName().equals("java.util.Date")){
                try {
                    System.out.println(field.getName());
                    System.out.println(field.getType().getName());
                    Object obj = field.get(data);
                    Long l = Date.parse(obj.toString());

                    System.out.println(obj.toString());
                    System.out.println(l);
                    field.setLong(data,l);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}