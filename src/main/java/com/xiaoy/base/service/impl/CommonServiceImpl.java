package com.xiaoy.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.service.CommonService;

public class CommonServiceImpl<T> implements CommonService<T> {

	private CommonDao<T> commonDao;
	
	public CommonDao<T> getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao<T> commonDao) {
		this.commonDao = commonDao;
	}

	public CommonServiceImpl() {
		super();
	}

	public CommonServiceImpl(CommonDao<T> commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public void deleteObjectByid(Serializable id) {
		commonDao.deleteObjectByid(id);
	}

	@Override
	public void deleteAll() {
		commonDao.deleteAll();
	}

	@Override
	public void deleteObjectByCollectionIds(String hqlWhere, Map<String, Object> paramsMapValue) {
		commonDao.deleteObjectByCollectionIds(hqlWhere, paramsMapValue);
	}

	@Override
	public void updateObject(T entity) {
		commonDao.updateObject(entity);
	}

	@Override
	public void updateObjectCollection(Collection<T> entities) {
		commonDao.updateObjectCollection(entities);
	}

	@Override
	public void saveObject(T entity) {
		commonDao.saveObject(entity);
	}

	@Override
	public void saveObjectCollection(Collection<T> entities) {
		commonDao.saveObjectCollection(entities);
	}

	@Override
	public void saveOrUpdate(T t) {
		commonDao.saveOrUpdate(t);
	}

	@Override
	public T findObjectById(Serializable id) {
		System.out.println("commonDao----------------->" + commonDao);
		return commonDao.findObjectById(id);
	}

	@Override
	public List<T> findCollectionByCondition(String hqlWhere, Map<String, Object> paramsMapValue) {
		return commonDao.findCollectionByCondition(hqlWhere, paramsMapValue);
	}

	@Override
	public Integer countByCollection(String hqlWhere, Map<String, Object> paramsMapValue) {
		return commonDao.countByCollection(hqlWhere, paramsMapValue);
	}
}
