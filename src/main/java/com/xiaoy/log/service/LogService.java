package com.xiaoy.log.service;

import java.util.List;

import com.xiaoy.base.entities.Log;
import com.xiaoy.base.service.CommonService;

/**
 * 日志记录
 * 
 * @author XiaoY
 * @date: 2015年8月16日 下午10:03:43
 */
public interface LogService extends CommonService<Log>
{
	/**
	 * AOP日志专用，不会记录到日志中
	 * 
	 * @param log
	 */
	public void persistLog(Log log);

	/**
	 * 根据条件查询出日志
	 * 
	 * @param log
	 *            查询日志
	 * @return
	 */
	public List<Log> findCollectionByCondition(Log log);
}
