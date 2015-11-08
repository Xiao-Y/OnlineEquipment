package com.xiaoy.aop.log;

import org.aspectj.lang.JoinPoint;

public interface LogAop {
	/**
	 * 有参的保存日志
	 * 
	 * @param point
	 */
	public void logArgSave(JoinPoint point);

	// /**
	// * 有参的更新方法的日志
	// *
	// * @param point
	// */
	// public void logArgUpdate(JoinPoint point);
	//
	// /**
	// * 有参的删除方法的日志
	// *
	// * @param point
	// */
	// public void logArgDelete(JoinPoint point);

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
