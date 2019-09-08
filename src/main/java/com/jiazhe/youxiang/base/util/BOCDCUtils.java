/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡工具类
 * @created 2019-09-08 9:30
 */
public class BOCDCUtils {

    private static String xml_prefix = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

    private static String data_begin = "<data>";

    private static String data_end = "</data>";

    public static String generateReturn(String result) {
        return xml_prefix + data_begin + result + data_end;
    }

}
