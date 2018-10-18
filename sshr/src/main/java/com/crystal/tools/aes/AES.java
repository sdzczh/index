package com.crystal.tools.aes;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.crystal.tools.base64.BASE64;

/**
 * @描述 AES(对称)加密解密<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-5
 */
public class AES {
	
	private AES(){}

	/**
	 * @描述 加密<br>
	 * @param data 明文
	 * @param key 向量
	 * @return 密文(BASE64加密)
	 * @throws Exception
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-5
	 */
	public static String encrypt(String data, String key) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key.getBytes()));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec secretKey2 = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		byte[] byteContent = data.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey2);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return BASE64.encoder(result); // 加密
	}
	
	/**
	 * @描述 解密<br>
	 * @param data 密文
	 * @param key 向量
	 * @return 明文
	 * @throws Exception
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-5
	 */
	public static String decrypt(String data, String key) throws Exception {  
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	    kgen.init(128, new SecureRandom(key.getBytes()));  
	    SecretKey secretKey = kgen.generateKey();  
	    byte[] enCodeFormat = secretKey.getEncoded();  
	    SecretKeySpec secretKey2 = new SecretKeySpec(enCodeFormat, "AES");              
	    Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
	    cipher.init(Cipher.DECRYPT_MODE, secretKey2);// 初始化  
	    byte[] result = cipher.doFinal(BASE64.decoderByte(data));  
	    return new String(result); // 加密  
	} 
	
	/**
	 * @描述 工具类测试方法<br>
	 * @param data 明文 可接收NULl值，默认:这是密文 This is test function
	 * @param key 向量 可接收NULl值，默认:Cr!ys#tal@
	 * @throws Exception
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-5
	 */
	public static void test(String data,String key) throws Exception{
    	if(data==null || "".equals(data)){
    		data="这是密文 This is test function";
    	}
    	if(key==null || "".equals(key)){
    		key="Cr!ys#tal@";
    	}
    	System.out.println("【明文】:"+data);
    	System.out.println("【向量】:"+key);
    	
    	String enStr = encrypt(data, key);
    	System.out.println("【密文】:"+enStr);
    	
    	String deStr = decrypt(enStr, key);
    	System.out.println("【解密明文】:"+deStr);
		System.out.println("*******************************************************");    	
	}
	
	public static void main(String[] args) throws Exception {
		test(null,null);
	}
}
