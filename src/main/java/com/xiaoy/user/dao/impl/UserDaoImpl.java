package com.xiaoy.user.dao.impl;

import java.util.List;

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

}
