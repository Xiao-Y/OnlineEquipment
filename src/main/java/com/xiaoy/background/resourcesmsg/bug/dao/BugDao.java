package com.xiaoy.background.resourcesmsg.bug.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Bug;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:49
 */
public interface BugDao extends CommonDao<Bug>
{
	/**
	 * 根据条件查询
	 * 
	 * @param bug
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Bug> findCollectionByCondition(Bug bug, String start, String limit);

	/**
	 * 根据条件统计出现记录数
	 * 
	 * @param bug
	 * @return
	 */
	public long countByCollection(Bug bug);
}
