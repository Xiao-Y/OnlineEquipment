package com.xiaoy.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 公接口<br>
 * 所有的Dao接口必须extends<br>
 * 方法：<br>
 * 根据id删除一个对<br>
 * public void deleteObjectByid(Serializable id);
 * <p>
 * 
 * 根据一个集合删除一组对象,可以通过hqlWhere添加查询条件，paramMapValue设置查询参数 <br>
 * public void deleteObjectByCollectionIds(String hqlWhere,Map<String, Object> paramsMapValue);
 * <p>
 * 
 * 更新一个对象<br>
 * public void updateObject(T entity);
 * <p>
 * 
 * 根据一个集合更新一组对象<br>
 * public void updateObjectCollection(Collection&ltT&gt entities);
 * <p>
 * 
 * 保存一个对象<br>
 * public void saveObject(T entity);
 * <p>
 * 
 * 根据一个集合对象保存一组对象<br>
 * public void saveObjectCollection(Collection&ltSerializable&gt entities);
 * <p>
 * 
 * 根据一个id查询出一个对象<br>
 * public T findObjectById(Serializable id);
 * <p>
 * 
 * 根据条件查询出一个集合（不分页）,可以通过hqlWhere添加查询条件，paramMapValue设置查询参数<br>
 * List&ltT&gt findCollectionByCondition(String hqlWhere,Map<String, Object> paramsMapValue);
 * <p>
 * 
 * @author XiaoY 2014年11月5日 下午10:58:20
 */
public interface CommonDao<T> {

	/**
	 * 获取session
	 * 
	 * @return
	 * 
	 * @date 2015年8月11日下午6:13:36
	 */
	public Session getSession();

	/**
	 * 通过id删除一个对象
	 * 
	 * @param id
	 *            任意类型的id
	 */
	public void deleteObjectByid(Serializable id);

	/**
	 * 删除所有
	 */
	public void deleteAll();

	/**
	 * 通过一个集合删除多个对象,可以通过hqlWhere添加删除条件，paramMapValue设置删除参数
	 * 
	 * @param hqlWhere
	 *            查询条件
	 * @param paramsMapValue
	 *            设置查询参数
	 */
	public void deleteObjectByCollectionIds(String hqlWhere, Map<String, Object> paramsMapValue);

	/**
	 * 更新一个对象
	 * 
	 * @param entity
	 *            泛型的
	 */
	public void updateObject(T entity);

	/**
	 * 根据一个集合更新一组对象
	 * 
	 * @param entities
	 *            将要更新的集合
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午5:01:24
	 */
	public void updateObjectCollection(Collection<T> entities);

	/**
	 * 保存一个对象
	 * 
	 * @param entity
	 *            泛型的
	 */
	public void saveObject(T entity);

	/**
	 * 根据一个集合对象保存或更新一组对象
	 * 
	 * @param entities
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午5:03:47
	 */
	public void saveOrUpdateObjectCollection(Collection<T> entities);

	/**
	 * 根据一个集合对象保存一组对象
	 * 
	 * @param entities
	 * @date 2015年10月6日 下午1:53:23
	 */
	public void saveObjectCollection(Collection<T> entities);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param t
	 * @date 2015年10月6日 下午1:53:37
	 */
	public void saveOrUpdate(T t);

	/**
	 * 通过id查询一个对象
	 * 
	 * @param id
	 *            任意类型
	 * @return 对象（泛型）
	 * 
	 * @author XiaoY
	 * @date: 2014年11月5日 下午10:04:01
	 */
	public T findObjectById(Serializable id);

	/**
	 * 带条件的查询，不分页。可以通过hqlWhere添加查询条件，paramMapValue设置查询参数
	 * 
	 * @param hqlWhere
	 *            查询条件
	 * @param paramsMapValue
	 *            设置查询参数
	 * @return List&ltT&gt
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午11:22:52
	 */
	public List<T> findCollectionByCondition(String hqlWhere, Map<String, Object> paramsMapValue);

	/**
	 * 带条件的查询，带分页。可以通过hqlWhere添加查询条件，paramMapValue设置查询参数
	 * 
	 * @param hqlWhere
	 *            查询条件
	 * @param paramsMapValue
	 *            设置查询参数
	 * @return List&ltT&gt
	 * 
	 * @author XiaoY
	 * @date: 2014年12月13日 下午11:22:52
	 */
	List<T> findCollectionByCondition(String string, Map<String, Object> paramsMapValue, String start, String limit);

	/**
	 * 带条件的查询，根据条件查询出现总记录数
	 * 
	 * @param hqlWhere
	 *            查询条件
	 * @param paramsMapValue
	 *            设置参数
	 * @return
	 */
	public Integer countByCollection(String hqlWhere, Map<String, Object> paramsMapValue);

	/**
	 * 通过Hql查询记录总数
	 * 
	 * @param hql
	 *            查询语句
	 * @param paramsMapValue
	 *            查询参数
	 * @return
	 */
	public Integer countHQLByCollection(String hql, Map<String, Object> paramsMapValue);

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
	public void settingParam(String hqlWhere, Map<String, Object> paramsMap, Query query);

	/**
	 * 通过hql语句查询
	 * 
	 * @param hql
	 *            自定义hql
	 * @param params
	 *            查询条件参数
	 * @param start
	 *            分页
	 * @param limit
	 *            分页
	 * @return
	 */
	List<T> find(String hql, Map<String, Object> params, String start, String limit);

}
