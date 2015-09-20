package com.xiaoy.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.util.GenericSuperclass;

/**
 * @author XiaoY
 * 
 * @explain 继承HibernateDaoSupport 实现CommonDao&ltT&gt
 * 
 * @date: 2014年11月5日 下午11:00:01
 */
public class CommonDaoImpl<T> implements CommonDao<T> {
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	// 获取当前类的父类的类型(泛型的转换)
	@SuppressWarnings("rawtypes")
	Class entityClass = GenericSuperclass.getClass(this.getClass());

	public void deleteObjectByid(Serializable id) {
		this.getSession().delete(this.getSession().get(entityClass, id));
	}

	public void deleteObjectByCollectionIds(String hqlWhere, Map<String, Object> paramsMapValue) {
		StringBuffer hql = new StringBuffer(" delete from " + this.entityClass.getSimpleName() + " where 1 = 1 ");

		hql.append(hqlWhere);

		Query query = this.getSession().createQuery(hql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0) {
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}

		query.executeUpdate();
	}

	public void updateObject(T entity) {
		this.getSession().update(entity);
	}

	public void saveObject(T entity) {
		this.getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	public T findObjectById(Serializable id) {
		T t = (T) this.getSession().get(entityClass, id);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<T> findCollectionByCondition(String hqlWhere, Map<String, Object> paramsMapValue) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getSimpleName() + " e where 1 = 1 ");
		if (!StringUtils.isEmpty(hqlWhere)) {
			hql.append(hqlWhere);
		}

		Query query = this.getSession().createQuery(hql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0) {
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		return query.list();
	}

	public Integer countHQLByCollection(String hql, Map<String, Object> paramsMapValue) {
		Query query = this.getSession().createQuery(hql);

		if (!StringUtils.isEmpty(hql) && paramsMapValue != null && paramsMapValue.size() > 0) {
			// 设置参数
			this.settingParam(hql, paramsMapValue, query);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}

	public Integer countByCollection(String hqlWhere, Map<String, Object> paramsMapValue) {
		StringBuffer sql = new StringBuffer(" select count(*) from " + entityClass.getSimpleName() + " e where 1 = 1 ");

		sql.append(hqlWhere);

		Query query = this.getSession().createQuery(sql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0) {
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(count.toString());
	}

	public void updateObjectCollection(Collection<T> entities) {
		if (entities != null && entities.size() > 0) {
			for (T t : entities) {
				this.getSession().update(t);
			}
		}

	}

	public void saveObjectCollection(Collection<T> entities) {
		if (entities != null && entities.size() > 0) {
			for (T t : entities) {
				this.getSession().saveOrUpdate(t);
			}
		}

	}

	public void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	/**
	 * 设置条件查询的参数
	 * 
	 * @param hqlWhere
	 *            条件hql
	 * @param paramsMap
	 *            参数集合
	 * @param query
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午7:52:53
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void settingParam(String hqlWhere, Map<String, Object> paramsMap, Query query) {
		if (!paramsMap.isEmpty() && paramsMap.size() > 0 && hqlWhere != null && hqlWhere.length() > 0) {
			for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
				if (entry.getValue() instanceof Collection) {
					query.setParameterList(entry.getKey(), (Collection) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public void deleteAll() {
		String hql = " delete from " + entityClass.getSimpleName();
		this.getSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> paramsMapValue, String start, String limit) {
		Query query = this.getSession().createQuery(hql);

		if (!StringUtils.isEmpty(hql) && paramsMapValue != null && paramsMapValue.size() > 0) {
			// 设置参数
			this.settingParam(hql, paramsMapValue, query);
		}

		if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(limit)) {
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
		}
		List<T> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findCollectionByCondition(String hqlWhere, Map<String, Object> paramsMapValue, String start, String limit) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getSimpleName() + " e where 1 = 1 ");

		hql.append(hqlWhere);

		Query query = this.getSession().createQuery(hql.toString());

		if (!StringUtils.isEmpty(hqlWhere) && paramsMapValue != null && paramsMapValue.size() > 0) {
			// 设置参数
			this.settingParam(hqlWhere, paramsMapValue, query);
		}

		if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(limit)) {
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
		}
		return query.list();
	}
}
