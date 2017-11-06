package com.crystal.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crystal.dao.inte.BaseDaoI;

@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl implements BaseDaoI {

	@Autowired
	public SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/**
	 * 获得当前事物的 session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T> Serializable save(T o) {
		if (o != null) {
			return getCurrentSession().save(o);
		}
		return null;
	}

	@Override
	public <T>T getById(Class<T> c, Serializable id) {
		return (T) getCurrentSession().get(c, id);
	}

	@Override
	public <T>T getByHql(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public <T>T getByHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public <T>void delete(T o) {
		if (o != null) {
			getCurrentSession().delete(o);
		}
	}

	@Override
	public <T>void update(T o) {
		if (o != null) {
			getCurrentSession().update(o);
		}
	}

	@Override
	public <T>void saveOrUpdate(T o) {
		if (o != null) {
			getCurrentSession().saveOrUpdate(o);
		}
	}

	@Override
	public <T>List<T> find(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		
		return q.list();
	}

	@Override
	public <T>List<T> find(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public <T>List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = getCurrentSession().createQuery(hql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult(page*rows).setMaxResults(rows).list();
	}

	@Override
	public <T>List<T> find(String hql, int page, int rows) {
		Query q = getCurrentSession().createQuery(hql);
		
		return q.setFirstResult(page*rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public List<?> findBySql(String sql) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<?> findBySql(String sql, int page, int rows) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		return q.setFirstResult(page*rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<?> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	

	@Override
	public <T>List<T> findBySql(Class<T> clazz, String sql,
			Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(clazz);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<?> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult(page*rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	

	@Override
	public <T>List<T> findBySql(Class<T> clazz, String sql,
			Map<String, Object> params, int page, int rows) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(clazz);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult(page*rows).setMaxResults(rows).list();
	}


	@Override
	public int executeSql(String sql) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		return q.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public BigInteger countBySql(String sql) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		return (BigInteger) q.uniqueResult();
	}

	@Override
	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

	@Override
	public Long countLongBySql(String sql) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		return Long.parseLong(q.uniqueResult().toString());
	}

	@Override
	public List<?> call(String sql) {
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<List<String>> call(String sql,Integer colCount, Map<String, String> map,List<String> outs) {
		final String sql2 = sql;
		final Map<String, String> map2 = map;
		final Integer colCount2 = colCount;
		final List<String> resulList = new ArrayList<String>();
//		final List<ResultSet> resultSets = new ArrayList<ResultSet>();
		final List<List<String>> list = new LinkedList<List<String>>();
		getCurrentSession().doWork(new Work() {
			
			@Override
			public void execute(Connection conn) throws SQLException {
				CallableStatement proc = null;
				try
	            {
	                proc = conn.prepareCall("{"+sql2+"}");	              

	                //注意：这里是传递输入参数
					if (map2 != null && !map2.isEmpty()) {
						for (String key : map2.keySet()) {
							proc.setString(Integer.parseInt(key), map2.get(key).toString());
						}
					}
					
	                //注意：这里是注册输出参数
					proc.registerOutParameter("ct", java.sql.Types.VARCHAR);
					ResultSet rs = proc.executeQuery();
	                //resultSets.add(proc.executeQuery());
					
					while (rs.next()) {
						List<String> row = new LinkedList<String>();
						for (int i = 1; i < colCount2+1; i++) {
							row.add(rs.getString(i));
						}
						list.add(row);
					}
					
            		resulList.add(proc.getString("ct"));

	            }
	            catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
	            finally
	            {
	                if(null != proc)
	                {
	                	proc.close();
	                }
	            }				
			}
		});
		outs.add(resulList.get(0));
		
		return  list;
	}


	/* (non-Javadoc)
	 * @see net.ssgm.ssyad.dao.BaseDaoI#findUnique(java.lang.String)
	 */
	@Override
	public <T>T findUnique(String hql) {
		List<T> list = find(hql);
		if (list.size()>0) {
			return list.get(0);
		}else{
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see net.ssgm.ssyad.dao.BaseDaoI#findUnique(java.lang.String, java.util.Map)
	 */
	@Override
	public <T>T findUnique(String hql, Map<String, Object> params) {
		List<T> list = find(hql,params);
		if (list.size()>0) {
			return list.get(0);
		}else{
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see net.ssgm.ssydl.dao.BaseDaoI#del(java.lang.Integer, java.lang.String)
	 */
	@Override
	public int del(Integer id, String tableName) {
		
		return executeSql(String.format("UPDATE %s SET delflag=1  WHERE id = %s ",tableName,id.toString()));
	}


	@Override
	public void test() {
		System.out.println("DAO");
	}




}
