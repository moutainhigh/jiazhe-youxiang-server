/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import java.math.BigDecimal;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/11/27
 */
public class MathUtil {

    public static BigDecimal getValue(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value;
    }

    public static Integer getValue(Integer value) {
        if (value == null) {
            return Integer.valueOf(0);
        }
        return value;
    }
}