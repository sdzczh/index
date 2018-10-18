package com.crystal.tools.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @描述 JSON格式化<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-7
 */
public class ResultUtils {
	
	private ResultUtils(){}
	
	/**
	 * @描述 JSON格式化<br>
	 * @param result 返回格式实体对象
	 * @return JSON格式字符串
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-7
	 */
	public static String formatJson(Result result){
		JSONObject json = (JSONObject) JSON.toJSON(result);
		return json.toJSONString();
	}
	
}
