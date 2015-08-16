package com.xiaoy.log.service;

import org.aspectj.lang.JoinPoint;

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
	 * 有参的保存方法的日志
	 * 
	 * @param point
	 */
	public void logArgSave(JoinPoint point);

	/**
	 * 有参的更新方法的日志
	 * 
	 * @param point
	 */
	public void logArgUpdate(JoinPoint point);

	/**
	 * 有参的删除方法的日志
	 * 
	 * @param point
	 */
	public void logArgDelete(JoinPoint point);

	// // 无参的日志方法
	// public void log();

	// /**
	// * 有参有返回值的方法
	// *
	// * @param point
	// * @param returnObj
	// */
	// public void logArgAndReturn(JoinPoint point, Object returnObj);
}
