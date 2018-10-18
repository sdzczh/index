package com.crystal.tools.datasource;

public class DataSourceContext {  
	
	/**
	 * 写数据库(主数据库),与Spring中数据库ID一致
	 */
	public static final String WRITE_DATASOURCE="writeDataSource";

	/**
	 * 读数据库(从数据库),与Spring中数据库ID一致
	 */
	public static final String READ_DATASOURCE="readDataSource";
	
    private static final ThreadLocal<String> context = new ThreadLocal<String>();  
    /** 
     * @Description: 设置数据源类型 
     * @param dataSourceType  数据库类型 
     * @return void 
     * @throws 
     */  
    public static void setDataSource(String dataSource) {  
    	context.set(dataSource);  
    }  
      
    /** 
     * @Description: 获取数据源类型 
     * @param  
     * @return String 
     * @throws 
     */  
    public static String getDataSource() {  
        return context.get();  
    }  
      
    /** 
     * @Description: 清除数据源类型 
     * @param  
     * @return void 
     * @throws 
     */  
    public static void clearDataSource() {  
    	context.remove();  
    }  
}  