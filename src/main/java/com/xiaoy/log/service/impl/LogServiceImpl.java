package com.xiaoy.log.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Log;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.log.dao.LogDao;
import com.xiaoy.log.service.LogService;

/**
 * 系统操作日志记录服务<br>
 * 使用了Spring AOP，对保存，更新，删除进行记录
 * 
 * @author XiaoY
 * @date: 2015年8月16日 下午11:22:44
 */
@Service
public class LogServiceImpl extends CommonServiceImpl<Log> implements LogService
{
	private LogDao logDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Log> commonDao)
	{
		this.logDao = (LogDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public void logArgSave(JoinPoint joinPoint)
	{
		Log log = new Log();
		log.setOperation("保存操作");
		this.saveLog(joinPoint, log);
	}

	@Override
	public void logArgUpdate(JoinPoint joinPoint)
	{
		Log log = new Log();
		log.setOperation("更新操作");
		this.saveLog(joinPoint, log);
	}

	@Override
	public void logArgDelete(JoinPoint joinPoint)
	{
		Log log = new Log();
		log.setOperation("删除操作");
		this.saveLog(joinPoint, log);
	}

	/**
	 * 保存日志
	 * 
	 * @param joinPoint
	 * @param log
	 */
	private void saveLog(JoinPoint joinPoint, Log log)
	{
		// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = joinPoint.getArgs();
		// 判断参数
		if (args == null)
		{// 没有参数
			return;
		}
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取操作内容
		String opContent = this.optionContent(joinPoint.getArgs(), methodName);

		log.setId(UUID.randomUUID().toString());
		log.setContent(opContent);
		log.setCreateTime(new Date());
		log.setUserId("1111");

		logDao.saveObject(log);
	}

	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	private String optionContent(Object[] args, String mName)
	{
		if (args == null)
		{
			return null;
		}
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args)
		{
			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[参数" + index + "，类型：" + className + "，值：");
			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();
			// 遍历方法，判断get方法
			for (Method method : methods)
			{
				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1)
				{// 不是get方法
					continue;// 不处理
				}
				Object rsValue = null;
				try
				{
					// 调用get方法，获取返回值
					rsValue = method.invoke(info);

					if (rsValue == null)
					{// 没有返回值
						continue;
					}
				} catch (Exception e)
				{
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