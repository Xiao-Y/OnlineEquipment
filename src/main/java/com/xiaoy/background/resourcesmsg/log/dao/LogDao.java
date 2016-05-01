package com.xiaoy.background.resourcesmsg.log.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.LogDto;

public interface LogDao extends CommonDao<LogDto> {
	/**
	 * 根据条件查询出日志
	 * 
	 * @param log
	 *            查询日志
	 * @return
	 */
	public List<LogDto> findCollectionByCondition(LogDto log, String start, String limit);

	/**
	 * 根据条件查询出日志记录数
	 * 
	 * @param log
	 *            查询日志
	 * @return
	 */
	public long countLog(LogDto log);
}
