package com.crystal.tools.redis;

import org.springframework.data.redis.core.RedisTemplate;

public class REDIS {

	/**
	 * @描述 String添加<br>
	 * @param redis Redis对象
	 * @param key Key值 
	 * @param value Value值
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-10-10
	 */
	public static void addString(RedisTemplate<String,Object> redis,String key,String value){
		redis.opsForValue().set(key, value);
	}
	
	/**
	 * @描述 String查询<br>
	 * @param redis Redis对象
	 * @param key Key值
	 * @param value Value值
	 * @return 查询结果
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-10-10
	 */
	public static String queryString(RedisTemplate<String,Object> redis,String key,String value){
		Object obj = redis.opsForValue().get(key);
		if(obj!=null){
			return obj.toString();
		}else{
			return null;
		}
	}
	
	/**
	 * @描述 String更新<br>
	 * @param redis Redis对象
	 * @param key Key值
	 * @param value Value值
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-10-10
	 */
	public static void updateString(RedisTemplate<String,Object> redis,String key,String value){
		redis.delete(key);
		redis.opsForValue().set(key,value);
	}
	
	public static void deleteString(RedisTemplate<String,Object> redis,String key){
		redis.delete(key);
	}
	
	/**
	 * @描述 List添加集合<br>
	 * @param redis Redis对象
	 * @param key Key值
	 * @param value Value值
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-10-10
	 */
	public static void addList(RedisTemplate<String,Object> redis,String key,String value){
		
	}
	
	/*
	public static void queryString(RedisTemplate<String,Object> redis,String key,String value){
		
	}
	
	public static void updateString(RedisTemplate<String,Object> redis,String key,String value){
		
	}
	
	public static void deleteString(RedisTemplate<String,Object> redis,String key){
		
	}
	
	*/
}
