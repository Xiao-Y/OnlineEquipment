package com.xiaoy.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.User;
import com.xiaoy.user.dao.UserDao;

@Repository
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getUserIdByName(String name)
	{
		String hql = "select id from User where username like :username";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("username", name);
		List<Object> list = query.list();
		return list;
	}

	@Override
	public User findByName(String loginName)
	{
		String hql = "select User from User where username = :username";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("username", loginName);
		return (User) query.uniqueResult();
	}

	@Override
	public List<User> findCollectionByCondition(User user, String start, String limit)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWher(hqlWhere, user);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
	}

	@Override
	public long countByCollection(User user)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWher(hqlWhere, user);
		return super.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	/**
	 * 拼接查询语句
	 * 
	 * @param hqlWhere
	 * @param user
	 * @return
	 */
	private Map<String, Object> appendWher(StringBuffer hqlWhere, User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
