package com.crystal.dao.inte;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public interface BaseDaoI {

	/**
	 * 获得当前事物的 session
	 * @return
	 */
	public Session getCurrentSession();
	
	public SessionFactory getSessionFactory() ;
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 * @return 对象的ID
	 */
	public <T> Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public <T> void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public <T> void update(T o);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public <T> void saveOrUpdate(T o);

	/**
	 * 通过主键获得对象
	 * 
	 * @param c
	 *            类名.class
	 * @param id
	 *            主键
	 * @return 对象
	 */
	public <T>T getById(Class<T> c, Serializable id);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 对象
	 */
	public <T>T getByHql(String hql);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 对象
	 */
	public <T>T getByHql(String hql, Map<String, Object> params);

	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @return List
	 */
	public <T>List<T> find(String hql);
	
	/**
	 * 获取唯一对象.如果有多个默认取第一个.
	 * @param hql
	 * @return
	 */
	public <T>T findUnique(String hql);

	/**
	 * 获取唯一对象.如果有多个默认取第一个.
	 * @param hql
	 * @return
	 */
	public <T>T findUnique(String hql,Map<String, Object> params);
	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return List
	 */
	public <T>List<T> find(String hql, Map<String, Object> params);

	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return List
	 */
	public <T>List<T> find(String hql, int page, int rows);

	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return List
	 */
	public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows);

	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T)
	 * @return long
	 */
	public Long count(String hql);

	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T where xx = :xx)
	 * @param params
	 *            参数
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 执行一条HQL语句
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 响应结果数目
	 */
	public int executeHql(String hql);

	/**
	 * 执行一条HQL语句
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 响应结果数目
	 */
	public int executeHql(String hql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 结果集
	 */
	public List<?> findBySql(String sql);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<?> findBySql(String sql, int page, int rows);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 结果集
	 */
	public List<?> findBySql(String sql, Map<String, Object> params);
	
	public <T>List<T> findBySql(Class<T> clazz, String sql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<?> findBySql(String sql, Map<String, Object> params, int page, int rows);
	
	public <T> List<T> findBySql(Class<T> clazz,String sql, Map<String, Object> params, int page, int rows);
	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 响应行数
	 */
	public int executeSql(String sql);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 响应行数
	 */
	public int executeSql(String sql, Map<String, Object> params);

	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 数目
	 */
	public BigInteger countBySql(String sql);
	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 数目
	 */
	public Long countLongBySql(String sql);

	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 数目
	 */
	public BigInteger countBySql(String sql, Map<String, Object> params);

	/**
	 * 调用存储过程.
	 * @param sql
	 * @return
	 */
	public List<?> call(String sql);
	
	public List<?> call(String sql,Integer colCount, Map<String, String> map,List<String> outs);
	
	/**
	 * 逻辑删除一条记录。要求表必须有delflag 字段,且字段类型为int. delflag=1表示删除.
	 * @param id 删除记录的id。
	 * @param tableName 表名。
	 */
	public int del(Integer id,String tableName);
	
	public void test();
}
