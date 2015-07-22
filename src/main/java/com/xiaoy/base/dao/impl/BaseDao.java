package com.xiaoy.base.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author XiaoY
 *
 * @explain 用于获取Session
 *
 * @date: 
 * 2014年11月6日 下午10:48:02
 */
public class BaseDao
{
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession()
	{
		System.out.println("this.sessionFactory.getCurrentSession();--------------------->" + this.sessionFactory.getCurrentSession());
		return this.sessionFactory.getCurrentSession();
	}
}
