package com.crystal.tools.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/***
 * @描述 读写分离辅助类
 * Spring+Hibernate多数据源动态切换类(DataSource类)<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-10-1
 * 
 * @例:
 * jdbc.properties:
 * #59.110.173.126
 * master_url=jdbc:mysql://59.110.173.126:6789/starcoin_test?useUnicode=true&characterEncoding=utf-8
 * master_username=TJwEMVcaIsU=
 * master_password=G8JnwaXjaxSv3Af/C9SSSw==
 * master_driverclass=com.mysql.jdbc.Driver
 * 
 * ##172.16.1.166
 * slave_url=jdbc:mysql://172.16.1.166:3309/starcoin_test?useUnicode=true&characterEncoding=utf-8
 * slave_username=TJwEMVcaIsU=
 * slave_password=TJwEMVcaIsU=
 * slave_driverclass=com.mysql.jdbc.Driver
 * 
 * XML文件:
 *     <!-- 配置主数据源 -->
 * 	<bean id="writeDataSource" parent="parentDataSource">      
 * 	    <property name="jdbcUrl" value="${master_url}"/>      
 * 	    <property name="user" value="${master_username}"/>      
 * 	    <property name="password" value="${master_password}"/>
 * 	    <property name="driverClass" value="${master_driverclass}"/>      
 * 	    <property name="minPoolSize" value="1" />
 * 		<property name="maxPoolSize" value="300" />
 * 		<property name="maxIdleTime" value="60" />
 * 		<property name="acquireIncrement" value="5" />
 * 		<property name="initialPoolSize" value="2" />
 * 		<property name="idleConnectionTestPeriod" value="60" /> 
 * 	</bean>  
 *     <!-- 配置从数据源 -->
 * 	<bean id="readDataSource" parent="parentDataSource">      
 * 	    <property name="jdbcUrl" value="${slave_url}"/>      
 * 	    <property name="user" value="${slave_username}"/>      
 * 	    <property name="password" value="${slave_password}"/>
 * 	    <property name="driverClass" value="${slave_driverclass}"/>      
 * 	    <property name="minPoolSize" value="1" />
 * 		<property name="maxPoolSize" value="300" />
 * 		<property name="maxIdleTime" value="60" />
 * 		<property name="acquireIncrement" value="5" />
 * 		<property name="initialPoolSize" value="2" />
 * 		<property name="idleConnectionTestPeriod" value="60" /> 
 * 	</bean>  
 * 
 * 	<!-- 数据源切换 -->
 *     <bean id="dataSource" class="cn.crystal.tools.datasource.DynamicDataSource">
 *         <property name="targetDataSources">  
 *               <map key-type="java.lang.String">  
 *                   <!-- write -->
 *                  <entry key="writeDataSource" value-ref="writeDataSource"/>  
 *                  <!-- read -->
 *                  <entry key="readDataSource" value-ref="readDataSource"/>  
 *               </map>  
 *         </property>  
 *         <property name="defaultTargetDataSource" ref="writeDataSource"/>  
 *     </bean>
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContext.getDataSource();
	}

}
