package com.crystal.tools.uuid;

/**
 * @描述 获取UUID<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-6
 */
public class UUID {

	private UUID(){}
	
	/**
	 * @描述 获取UUID字符串<br>
	 * @return UUID
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-6
	 */
	public static String randomUUID(){
		String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
