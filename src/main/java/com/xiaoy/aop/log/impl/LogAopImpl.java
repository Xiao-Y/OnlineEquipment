package com.xiaoy.aop.log.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import com.xiaoy.annotations.SystemControllerLog;
import com.xiaoy.aop.log.LogAop;
import com.xiaoy.background.resourcesmsg.log.service.LogService;
import com.xiaoy.base.entities.Log;
import com.xiaoy.util.LoginHelper;

@Component
public class LogAopImpl implements LogAop {
	@Resource
	private LogService logService;

	@Override
	public void logArgSave(JoinPoint joinPoint) {
		Log log = new Log();
		this.saveLog(joinPoint, log);
	}

	/**
	 * 保存日志
	 * 
	 * @param joinPoint
	 * @param log
	 * @throws Exception
	 */
	private void saveLog(JoinPoint joinPoint, Log log) {
		// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = joinPoint.getArgs();
		// 判断参数
		if (args == null) {// 没有参数
			return;
		}
		Map<String, String> map = null;
		try {
			map = this.getControllerMethodDescription(joinPoint);
			// map为空说明当前请求方法上没有日志注解
			if (map == null || map.isEmpty()) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取类全名
		String className = joinPoint.getTarget().getClass().getName();
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取操作内容
		String opContent = this.optionContent(joinPoint.getArgs());

		System.out.println(map.get("module") + "--->" + map.get("function") + "--->" + map.get("operation"));

		log.setId(UUID.randomUUID().toString());
		log.setContent(opContent);
		log.setRunClass(className + "." + methodName);
		log.setModal(map.get("module"));
		log.setFunction(map.get("function"));
		log.setOperation(map.get("operation"));
		log.setCreateTime(new Date());
		log.setUserId(LoginHelper.getLoginUserId());

		logService.persistLog(log);
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
			if ("Request".equals(className) || "Response".equals(className)) {
				continue;
			}
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

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, String> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		// 获取请求访求名
		String methodName = joinPoint.getSignature().getName();
		// 获取请求的处理类
		String targetName = joinPoint.getTarget().getClass().getName();
		// 获取请求访求的参数
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		// 获取处理类的所有方法
		Method[] methods = targetClass.getMethods();
		Map<String, String> map = new HashMap<String, String>();
		for (Method method : methods) {
			// 判断请求方法与方法数组中的方法是否相同
			if (method.getName().equals(methodName)) {
				// 获取方法数组中方法的参数
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					// 判断方法上是否存在自定义注解
					if (method.isAnnotationPresent(SystemControllerLog.class)) {
						map.put("module", method.getAnnotation(SystemControllerLog.class).module());
						map.put("function", method.getAnnotation(SystemControllerLog.class).function());
						map.put("operation", method.getAnnotation(SystemControllerLog.class).operation());
						return map;
					}
				}
			}
		}
		return map;
	}
}
