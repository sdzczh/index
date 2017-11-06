package com.crystal.tools.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @描述 日期时间工具<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-6-8
 */
public class DATE{
	
	private DATE(){}
	
	private static final String CURRENT_DATE_STR = "yyyy-MM-dd";
	private static final String CURRENT_TIME_STR = "yyyy-MM-dd HH:mm:ss";

	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * @描述 获取字符串格式的当前
	 * @return XXXX-XX-XX XX:XX:XX
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-6-13
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_TIME_STR);
		return format.format(new Date());
	}

	/**
	 * @描述 获取字符串格式的当前
	 * @return XXXX-XX-XX
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static String getCurrentDateStr() {
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_DATE_STR);
		return format.format(new Date());
	}
	
	public static String getCurrentMillTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}

	/**
	 * @描述 字符串转时间 
	 * @param str 例:XXXX-XX-XX XX:XX:XX
	 * @return 日期对象
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static Date formatStrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @描述 获取当前月份第一天
	 * @return 
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-7-5
	 */
	public static String getCurrentMonthStartDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		 String startDay = format.format(cal_1.getTime());
		return startDay;
	}
	
	/**
	 * @描述 测试方法<br>
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-5
	 */
	public static void test() {
		Date currentDate = getCurrentDate();
		System.out.println("【当前日期时间对象】:"+currentDate);
		
		String currentDateStr = getCurrentDateStr();
		System.out.println("【当前日期时间】:"+currentDateStr);
		
		Date date = formatStrToDate("2017-08-05 12:53:18");
		System.out.println("【字符串】:2017-08-05 12:53:18");
		System.out.println("【转化日期对象】:"+date);
		
		String currentMonthFirstDay = getCurrentMonthStartDay();
		System.out.println("【当前月第一天】:"+currentMonthFirstDay);
		System.out.println("*******************************************************");
	}

	public static void main(String[] args) {
		System.out.println(getCurrentMillTime());
		test();
	}

}
