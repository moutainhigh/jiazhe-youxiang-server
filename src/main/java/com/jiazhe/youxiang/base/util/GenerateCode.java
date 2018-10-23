package com.jiazhe.youxiang.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by TU on 2018/9/13.
 * 生成各种编码的工具类
 */
public class GenerateCode {

    /**
     * 1为【0,1】，0代表充值卡兑换码，1代表代金券兑换码；2-3为时间戳的最后两位；4-5为月；6-7为年的后两位；8-9为日，10-16为随机数
     * @param type 充值卡兑换码或代金券兑换码
     * @param n 兑换码个数
     * @return
     */
    public static String[][] generateCode (String type , Integer n){
        String result[][] = new String[2][n];
        Calendar now = Calendar.getInstance();
        String year = now.get(Calendar.YEAR)+"";
        String month = (now.get(Calendar.MONTH) + 1)<10? "0"+(now.get(Calendar.MONTH) + 1):""+(now.get(Calendar.MONTH) + 1);
        String day = now.get(Calendar.DAY_OF_MONTH)<10? "0"+now.get(Calendar.DAY_OF_MONTH):""+now.get(Calendar.DAY_OF_MONTH);
        String prefix = type + String.format("%02d", System.currentTimeMillis()%100) +  month + year.substring(2, 4) + day  ;
        HashSet<Integer> setCode = new HashSet<Integer>();
        HashSet<Integer> setKeyt = new HashSet<Integer>();
        randomSet(0,9999999,n, setCode);
        randomSet(0,9999999,n, setKeyt);
        int i=0;
        int j=0;
        for(Integer tempCode : setCode){
            result[0][i] = prefix+String.format("%07d", tempCode);
            i++;
        }
        for(Integer tempKeyt : setKeyt){
            result[1][j] = prefix+String.format("%07d", tempKeyt);
            j++;
        }
        return result;
    }

    /**
     * 从min到max，生成n个不同的整数
     * @param min 最大值
     * @param max 最小值
     * @param n 个数
     * @param set 返回值放在入参里
     */
    public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            // 将不同的数存入HashSet中
            set.add(num);
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, set);
        }
    }

}
