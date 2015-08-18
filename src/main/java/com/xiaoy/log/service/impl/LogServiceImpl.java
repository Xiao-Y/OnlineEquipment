package com.xiaoy.log.service.impl;

import javax.annotation.Resource;

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
	public void persistLog(Log log)
	{
		logDao.saveObject(log);
	}
}
