package com.jiazhe.youxiang.base.util;

/**
 * @author TU
 * @description 校验码算法,
 * @date 2019/2/20.
 */
public class CheckCodeAlgorithms {

    /**
     * 15位数字本体码权重，自定义
     */
    public static int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8};

    /**
     * mod11,对应校验码字符值
     */
    public static char[] validate = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * code长度不能大于weight的长度
     *
     * @param code
     * @return
     */
    public static char getValidateCode(String code) {
        int sum = 0;
        int mode = 0;
        if (code.length() > weight.length) {
            return 0;
        }
        for (int i = 0; i < code.length(); i++) {
            sum = sum + Integer.parseInt(String.valueOf(code.charAt(i))) * weight[i];
        }
        mode = sum % 10;
        return validate[mode];
    }

}
