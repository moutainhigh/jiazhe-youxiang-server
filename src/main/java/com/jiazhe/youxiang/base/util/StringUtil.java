package com.jiazhe.youxiang.base.util;

import org.apache.logging.log4j.util.Strings;

/**
 * Created by TU on 2018/8/30.
 * 字符串工具类
 */
public class StringUtil {

    //null转为空字符串
    public static String nullToEmptyStr(String str) {
        if (null == str) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * byte[] 转16进制字符串
     */
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1) {
            throw new IllegalArgumentException("this byteArray must not be null or empty");
        }
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toUpperCase();
    }

    public static byte[] toByteArray(String hexString) {
        if (Strings.isEmpty(hexString)) {
            throw new IllegalArgumentException("this hexString must not be empty");
        }
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    //中行给的公钥，转为我们系统定义的公钥
    public static void main(String[] args){
        String hexKey = "3081CF300D06092A864886F70D01010105000381BD003081B90281B100BDB22F8F485B66D92EA79D76540295DA23DA1188C0E97EF95DB7A2533F5CE00F9131A1068D920A2E6D0D7FC74CCE0E5C66EA131A48D9435ABAC25AD113EDFC5A423B921E675FE0A87EC65C15565C0284572EF90A8D01E053861B830334DABE0540007100804FC0B90B0F942BDF660CFC3449B7D74A3CC636A3F771BB05E5F57DC1BCD823C759CD7D354CEEC4484387E2CF5D28C8FD08D84EA266FCE4D33549AC202ED488DED510178253541BD500858B0203010001";
        byte[] arr = toByteArray(hexKey);
        String byte2Base64 = RSAUtil.byte2Base64(arr);
        System.out.println(byte2Base64);
    }
}
