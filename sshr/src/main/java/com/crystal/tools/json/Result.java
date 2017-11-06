package com.crystal.tools.json;

import java.io.Serializable;
import java.util.Map;

/**
 * @描述 返回格式<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-7
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回类型
	 */
	private Integer type;
	
	/**
	 * 数据
	 */
	private Map<String,Object> data;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
