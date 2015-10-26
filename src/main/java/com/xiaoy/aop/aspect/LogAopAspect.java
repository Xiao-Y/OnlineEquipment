package com.xiaoy.aop.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.xiaoy.annotations.SystemControllerLog;
import com.xiaoy.aop.log.HandleTyepEnum;
import com.xiaoy.background.resourcesmsg.log.service.LogService;
import com.xiaoy.base.entities.Log;
import com.xiaoy.util.LoginHelper;

/**
 * 切点类
 * 
 * @author XiaoY
 * @date: 2015年10月26日 下午6:04:50
 */
@Aspect
@Component
public class LogAopAspect {

	@Resource
	private LogService logService;

	/**
	 * controller层切点类
	 * 
	 * @date 2015年10月26日 下午6:06:10
	 */
	@Pointcut("@annotation(com.xiaoy.annotations.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 * @date 2015年10月26日 下午6:08:50
	 */
	@Before("controllerAspect()")
	public void logArgDelete(JoinPoint joinPoint) {
		Log log = new Log();
		log.setOperation(HandleTyepEnum.DELERE.getValue());
		this.saveLog(joinPoint, log);
	}

	/**
	 * 保存日志
	 * 
	 * @param joinPoint
	 * @param log
	 */
	private void saveLog(JoinPoint joinPoint, Log log) {
		// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = joinPoint.getArgs();
		// 判断参数
		if (args == null) {// 没有参数
			return;
		}
		// 获取超类的全类名
		// String methodName = joinPoint.getSignature().getDeclaringTypeName();

		// 获取类全名
		String className = joinPoint.getTarget().getClass().getName();
		System.out.println(className);
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName);
		// ------2015-10-26 修改---start-----------------
		try {
			Class<?> clazz = Class.forName(className);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				System.out.println(method.getName());
				if (method.getName().equals(methodName)) {
					if (method.isAnnotationPresent(SystemControllerLog.class)) {
						SystemControllerLog systemControllerLog = method.getAnnotation(SystemControllerLog.class);
						System.out.println(systemControllerLog.module() + "-->" + systemControllerLog.function() + "-->" + systemControllerLog.operation());
						break;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// ------2015-10-26 修改---end-----------------
		// 获取操作内容
		String opContent = this.optionContent(joinPoint.getArgs());

		log.setId(UUID.randomUUID().toString());
		log.setContent(opContent);
		log.setRunClass(className + "." + methodName);
		log.setCreateTime(new Date());
		log.setUserId(LoginHelper.getLoginUserId());

		// logService.persistLog(log);
	}

	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	private String optionContent(Object[] args) {
		if (args == null) {
			return null;
		}
		StringBuffer rs = new StringBuffer();
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args) {
			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[参数" + index + "，类型：" + className + "，值：");
			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();
			// 遍历方法，判断get方法
			for (Method method : methods) {
				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;// 不处理
				}
				Object rsValue = null;
				try {
					// 调用get方法，获取返回值
					rsValue = method.invoke(info);
					if (rsValue == null) {// 没有返回值
						continue;
					}
				} catch (Exception e) {
					continue;
				}
				// 将值加入内容中
				rs.append("(" + methodName + " : " + rsValue + ")");
			}
			rs.append("]");
			index++;
		}
		return rs.toString();
	}

	//
	// @Override
	// public void log()
	// {
	// System.out.println("*************Log*******************");
	// }

	// // 有参并有返回值的方法
	// @Override
	// public void logArgAndReturn(JoinPoint joinPoint, Object returnObj)
	// {
	// // 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
	// Object[] args = joinPoint.getArgs();
	// // 判断参数
	// if (args == null)
	// {// 没有参数
	// return;
	// }
	// // 获取方法名
	// String methodName = joinPoint.getSignature().getName();
	// // 获取操作内容
	// String opContent = this.optionContent(joinPoint.getArgs(), methodName);
	// // 添加日志
	// Log log = new Log();
	// log.setId(UUID.randomUUID().toString());
	// log.setContent(opContent);
	// log.setCreateTime(new Date());
	// log.setUserId("1111");
	// log.setOperation(methodName);
	// logDao.saveObject(log);
	// }
}
