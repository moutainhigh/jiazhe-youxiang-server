package com.coupon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {

	/*
	 * 将字符串转为date类型
	 */
	public static Date strToDate (String str) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		return sdf.parse(str);
	}
	
	/*
	 * 将字符串转为date类型
	 */
	public static Date strToMinutes(String str) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//小写的mm表示的是分钟  
		return sdf.parse(str);
	}
	
	/*
	 * 将字符串转为date类型
	 */
	public static Date strToHour(String str) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");//小写的mm表示的是分钟  
		return sdf.parse(str);
	}
	
	//返回yyyyMMDDhhmmss
	public static String yyyyMMDDhhmmss() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");//小写的mm表示的是分钟  
		String str = sdf.format(new Date());
		return str; 
	}
	
	//返回yyyyMMDDhhmmss
	public static String yyyyMMDDhhmmssSSS() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");//小写的mm表示的是分钟  
		String str = sdf.format(new Date());
		return str; 
	}
	
	//返回yyyyMMDDhhmmss
	public static String yyyy_MM_dd() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String str = sdf.format(new Date());
		return str; 
	}
	
}
