package com.jiazhe.youxiang.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author tu
 * @description：
 * @date 2018/12/22
 */
public class Test {
    public static void main(String[] args){
        BigDecimal needPay = new BigDecimal(12);
        BigDecimal conversionRate = new BigDecimal(4);
        System.out.print(needPay.divide(conversionRate).setScale(0, RoundingMode.HALF_UP));

        needPay.subtract(conversionRate).setScale(0).compareTo(needPay.subtract(conversionRate));
    }
}
