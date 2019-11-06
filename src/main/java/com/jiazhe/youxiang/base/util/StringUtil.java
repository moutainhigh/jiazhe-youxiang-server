package com.jiazhe.youxiang.base.util;

import org.apache.logging.log4j.util.Strings;
import org.bouncycastle.util.encoders.Hex;

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
     * 中行给的公钥，转为我们系统定义的公钥编码格式 base64
     */
    public static void main(String[] args) {
        String hexKey = "3081CF300D06092A864886F70D01010105000381BD003081B90281B100BDB22F8F485B66D92EA79D76540295DA23DA1188C0E97EF95DB7A2533F5CE00F9131A1068D920A2E6D0D7FC74CCE0E5C66EA131A48D9435ABAC25AD113EDFC5A423B921E675FE0A87EC65C15565C0284572EF90A8D01E053861B830334DABE0540007100804FC0B90B0F942BDF660CFC3449B7D74A3CC636A3F771BB05E5F57DC1BCD823C759CD7D354CEEC4484387E2CF5D28C8FD08D84EA266FCE4D33549AC202ED488DED510178253541BD500858B0203010001";
        byte[] arr = Hex.decode(hexKey);
        String byte2Base64 = RSAUtil.byte2Base64(arr);
        System.out.println(byte2Base64);
    }

}
