package com.jiazhe.youxiang.base.util;

import net.sf.json.JSONObject;

public class ResultPackage {

	public static String resultPackage(String code , Object data , String msg){
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		obj.put("data", data);
		return obj.toString() ;
	}
}
