package com.xiaoy.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解<br/>
 * 用于记录controller层的操作
 * 
 * @author XiaoY
 * @date: 2015年10月26日 下午5:18:09
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
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

	/**
	 * 备注
	 * 
	 * @return
	 * @date 2015年11月8日 下午2:56:05
	 */
	String note() default "";
}
