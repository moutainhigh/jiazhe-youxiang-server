package com.jiazhe.youxiang.base.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import springfox.documentation.spring.web.json.Json;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/*import org.springframework.data.domain.Page;*/

/**
 * Created by TU on 2018/8/30.
 */
public class PageFormatUtil {
  //将[{key1:value1,key2:value2},{key3:value3,key4:value4},{key5:value5,key6:value6}]格式的数据
  // 转为[[value1,value2],[value3,value4],[value5,value6]]，方便前台表格显示！！！
  public static JSONArray format(List<Map> Map) {
       JSONArray jsonArray = new JSONArray();
       for(Map temp:Map){
           JSONArray jsonArrayTemp = new JSONArray();
           JSONObject json = JSONObject.fromObject(temp);
           Iterator<String> it = json.keys();
           while(it.hasNext()){
               String key = it.next();
               String value = json.getString(key);
               jsonArrayTemp.add(value);
           }
           jsonArray.add(jsonArrayTemp);
       }
       return jsonArray;
    }

}
