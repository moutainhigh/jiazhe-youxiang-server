/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util.boccc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tu
 * @version 1.0
 * @description 根据不同环境不同的环境变量
 * @created 2019-09-05 19:08
 */
@Component
public class EnvironmentConstants {

    public static String PASSPHRASE ;

    @Value("${boccc.pgp.passphrase}")
    public void setPassPhrase(String passphrase) {
        PASSPHRASE = passphrase;
    }

}
