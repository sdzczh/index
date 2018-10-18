package com.crystal.utils;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.crystal.tools.des.DES;

/**
 * @描述 properties文件加密解密<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-6-7
 */
public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private static final  Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);
	
	private static final String PWD_KEY = "com.!@#ant#@!";
	
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		
		String username =  props.getProperty("username");  
		String password = props.getProperty("password");  
		  
        if (password != null) {
            
            String strPwd = "" ; 
            String strUserName = "";
			try {
				strUserName = DES.decrypt(username,PWD_KEY);
				strPwd = DES.decrypt(password,PWD_KEY);
			} catch (Exception e) {
				logger.error(e.getMessage());
				//if (SysConstant.debug) {
					e.printStackTrace();
				//}				
			}
            
            props.setProperty("password", strPwd);
            props.setProperty("username", strUserName);  
        }
        
	        
		super.processProperties(beanFactoryToProcess, props);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(DES.encrypt("antuser",PWD_KEY));
		System.out.println(DES.encrypt("ant!@#",PWD_KEY));
		System.out.println(DES.encrypt("foobared",PWD_KEY));
	}
}
