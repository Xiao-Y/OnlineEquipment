package com.xiaoy.annotations;

/**
 * 自定义注解<br/>
 * 用于记录controller层的操作
 * 
 * @author XiaoY
 * @date: 2015年10月26日 下午5:18:09
 */
public @interface SystemControllerLog {

	/**
	 * 模块名
	 * 
	 * @return
	 * @date 2015年10月26日 下午5:19:33
	 */
	String module();

	/**
	 * 功能
	 * 
	 * @return
	 * @date 2015年10月26日 下午5:19:42
	 */
	String function();

	/**
	 * 操作
	 * 
	 * @return
	 * @date 2015年10月26日 下午5:20:26
	 */
	String operation();
}
