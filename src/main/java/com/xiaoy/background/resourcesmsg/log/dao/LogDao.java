package com.xiaoy.background.resourcesmsg.log.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Log;

public interface LogDao extends CommonDao<Log> {
	/**
	 * 根据条件查询出日志
	 * 
	 * @param log
	 *            查询日志
	 * @return
	 */
	public List<Log> findCollectionByCondition(Log log, String start, String limit);

	/**
	 * 根据条件查询出日志记录数
	 * 
	 * @param log
	 *            查询日志
	 * @return
	 */
	public long countLog(Log log);
}
