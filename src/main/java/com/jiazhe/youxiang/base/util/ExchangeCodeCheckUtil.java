package com.jiazhe.youxiang.base.util;

/**
 * @author TU
 * @description 兑换码的卡号密钥检查
 * @date 2019/2/26.
 */
public class ExchangeCodeCheckUtil {

    /**
     * 老规则的卡号长度
     */
    public static Integer OLD_CODE_LENGTH = 16;

    /**
     * 老规则的密码长度
     */
    public static Integer OLD_KEYT_LENGTH = 16;

    /**
     * 新规则的卡号长度
     */
    public static Integer NEW_CODE_LENGTH = 8;

    /**
     * 新规则的密码长度
     */
    public static Integer NEW_KEYT_LENGTH = 12;

    /**
     * 预检查，检查卡号的正确性
     *
     * @param type
     * @param code
     * @return
     */
    public static boolean codeCheck(String type, String code) {
        if (code.length() != OLD_CODE_LENGTH && code.length() != NEW_CODE_LENGTH) {
            return false;
        }
        if (!type.equals(code.substring(0, 1))) {
            return false;
        }
        if (code.length() == NEW_CODE_LENGTH) {
            if (CheckCodeAlgorithms.getValidateCode(code.substring(0, code.length() - 1)) != code.charAt(code.length() - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 预检查，检查密码的正确性
     *
     * @param type
     * @param keyt
     * @return
     */
    public static boolean keytCheck(String type, String keyt) {
        if (keyt.length() != OLD_KEYT_LENGTH && keyt.length() != NEW_KEYT_LENGTH) {
            return false;
        }
        if (!type.equals(keyt.substring(0, 1))) {
            return false;
        }
        if (keyt.length() == NEW_KEYT_LENGTH) {
            if (CheckCodeAlgorithms.getValidateCode(keyt.substring(0, keyt.length() - 1)) != keyt.charAt(keyt.length() - 1)) {
                return false;
            }
        }
        return true;
    }
}
