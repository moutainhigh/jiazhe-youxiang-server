package com.jiazhe.youxiang.base.util;

/**
 * Created by TU on 2018/8/1.
 * 随机工具
 */
public class RandomUtil {

    private static char[] codeSequence = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /*
    生成四位随机验证码
    param n:验证码位数
     */
    public static String generateVerifyCode(int n) {
        StringBuilder verifyCode = new StringBuilder("");
        int length = codeSequence.length;//随机数生成范围【0-length】之间
        for(int i=0;i<n;i++){
            int random = (int)(Math.random()*length);
            verifyCode.append(codeSequence[random]);
        }
        return verifyCode.toString();
    }

}
