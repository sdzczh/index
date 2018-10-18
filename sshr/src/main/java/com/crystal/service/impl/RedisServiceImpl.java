package com.crystal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ser.std.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.crystal.dao.inte.BaseDaoI;
import com.crystal.entity.SSHR;
import com.crystal.entity.User;
import com.crystal.service.inte.RedisService;
import com.crystal.tools.date.DATE;
@SuppressWarnings({"unchecked","unused","rawtypes"})
@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
    private BaseDaoI dao;
    
    @Autowired
    private RedisTemplate<String,Object> redis;  

	@Override
	public void todo(String action) {
		action = action.toLowerCase();
		if(action.contains("string")){
			string(action);
			return;
		}
		if(action.contains("hash")){
			hash(action);
		}
		if(action.contains("list")){
			list(action);
		}
		
		if(action.contains("user")){
			user(action);
		}
	}

	public void string(String action){
		redis.setDefaultSerializer(new StringRedisSerializer());
		ValueOperations<String, Object> ops = redis.opsForValue();
		if(action.contains("string-save")){
			redis.opsForValue().set("key1","value11");  
			redis.opsForValue().set("key2","value2");  
			redis.opsForValue().set("key3","value3");  
			redis.opsForValue().set("key4","value44");  
			redis.opsForValue().set("test", "100",60,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间 
		}
		if(action.contains("string-search")){
			String result1=redis.opsForValue().get("key1").toString();  
			String result2=redis.opsForValue().get("key2").toString();  
			String result3=redis.opsForValue().get("key3").toString();  
			Set<String> keys = redis.keys("key*");
			System.out.println(keys.size());
			
			Object obj = redis.opsForValue().get("asdfasdfasdf");
			System.out.println(obj==null);
			
		}
		if(action.contains("string-update")){
			String str = (String) redis.opsForValue().get("key1");
			redis.delete("key1");
			str = str+"_news";
			redis.opsForValue().set("key1", str);
		}
		if(action.contains("string-delete")){
			redis.delete("key3");
		}
	}
	public void hash(String action){
		HashOperations<String,String, Object> ops = redis.opsForHash();
		if(action.contains("hash-save")){
			Map<String,Object> map = new HashMap<>();
			map.put("name", "Liasss");
			map.put("age", "1888");
			ops.putAll("class:one", map);
			
			map = new HashMap<>();
			map.put("name", "Jsone");
			map.put("age", "21");
			ops.putAll("class:two", map);
			
			map = new HashMap<>();
			SSHR sshr = new SSHR();
			sshr.setCode("7895642");
			sshr.setId(5);
			sshr.setPhone("15063784567");
			sshr.setTime(DATE.getCurrentTimeStr());
			map.put("U5468792:567", JSON.toJSON(sshr).toString());
			ops.putAll("class:three", map);
		}
		if(action.contains("hash-search")){
			 Map<Object, Object> resultMap= redis.opsForHash().entries("class:one");  
			 Map<Object, Object> resultMap2= redis.opsForHash().entries("class:three");  
		     List<Object>reslutMapList=redis.opsForHash().values("class:one");  
		     Set<Object>resultMapSet=redis.opsForHash().keys("class:one");  
		     String value=(String)redis.opsForHash().get("class:two","name");  
		     System.out.println("value:"+value);  
		     System.out.println("resultMapSet:"+resultMapSet);  
		     System.out.println("resultMap:"+resultMap);  
		     System.out.println("resulreslutMapListtMap:"+reslutMapList);  		
		     System.out.println(resultMap2.get("U5468792"));
		     
		     Set<String> keys = redis.keys("class:*");
		     System.out.println(keys.size());
		}
		if(action.contains("hash-update")){
			redis.delete("class:three");
			Map<String,Object> map = new HashMap<>();
			map.put("U5468792", "test");
			ops.putAll("class:three", map);
		}
		if(action.contains("hash-delete")){
			redis.boundHashOps("class:one").delete("age");
			redis.delete("class:one");
		}
	}
	
	
	public void list(String action){
		if(action.contains("list-save")){
			
			redis.opsForList().leftPushAll("push_all", "a","b","c","d");
			
			for(int i=1;i<=5;i++){
				SSHR sshr = new SSHR();
				sshr.setCode("Abac_"+i);
				sshr.setId(i);
				sshr.setPhone("1500000000"+i);
				sshr.setTime(DATE.getCurrentDateStr());
				redis.opsForList().leftPush("list", JSON.toJSON(sshr).toString());
			}
			for(int i=6;i<=10;i++){
				SSHR sshr = new SSHR();
				sshr.setCode("Abac_"+i);
				sshr.setId(i);
				sshr.setPhone("1500000000"+i);
				sshr.setTime(DATE.getCurrentDateStr());
				redis.opsForList().rightPush("list", JSON.toJSON(sshr).toString());
			}
		}
		if(action.contains("list-search")){
			List<Object> list = redis.opsForList().range("star_users", 0, 2);
			for (Object object : list) {
				System.out.println(object);
			}
		}
		if(action.contains("list-update")){
			SSHR sshr = new SSHR();
			sshr.setCode("Abac_1");
			sshr.setId(20);
			sshr.setPhone("15000000001");
			sshr.setTime("2017-10-09");
			String key = JSON.toJSONString(sshr);
			redis.opsForList().leftPush("list_test", key);
			System.out.println("-----");
			redis.opsForList().remove("list_test", 1l, key);
		}
		if(action.contains("list-delete")){
			for(int i=1;i<=10;i++){
				SSHR sshr = new SSHR();
				sshr.setCode("Abac_"+i);
				sshr.setId(i);
				sshr.setPhone("1500000000"+i);
				sshr.setTime(DATE.getCurrentDateStr());
				redis.opsForList().remove("list", 1l, JSON.toJSON(sshr).toString());
			}
		}
	}
	
	
	public void user(String action){
		
		if(action.contains("user-save")){//List强制JSON格式
			List<User> users = dao.find("from User where role=0");
			redis.setValueSerializer(new JacksonJsonRedisSerializer(User.class));
				redis.opsForList().leftPushAll("star_users", users.get(0),users.get(1),users.get(2));
			
			
		}
		
		if(action.contains("user-search")){
			
		}
		
		if(action.contains("user-delete")){
			
		}
		
		if(action.contains("user-update")){
			
		}
		
		
	}
}
