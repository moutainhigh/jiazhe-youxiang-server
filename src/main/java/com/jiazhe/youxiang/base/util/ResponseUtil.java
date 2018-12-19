package com.jiazhe.youxiang.base.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
	public static void responseUtils(HttpServletResponse response, String result) throws IOException{
		response.setContentType("application/json");
	 	response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}
}
