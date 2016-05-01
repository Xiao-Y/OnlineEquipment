package com.xiaoy.background.resourcesmsg.log.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.log.dao.LogDao;
import com.xiaoy.background.resourcesmsg.log.service.LogService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.LogDto;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.util.Tools;

/**
 * 系统操作日志记录服务<br>
 * 使用了Spring AOP，对保存，更新，删除进行记录
 * 
 * @author XiaoY
 * @date: 2015年8月16日 下午11:22:44
 */
@Service
public class LogServiceImpl extends CommonServiceImpl<LogDto> implements LogService {
	private LogDao logDao;

	@Resource
	private HttpServletRequest request;

	@Resource
	@Override
	public void setCommonDao(CommonDao<LogDto> commonDao) {
		this.logDao = (LogDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public void persistLog(LogDto log) {
		String ip = Tools.toIpAddr(request);
		log.setIpAddr(ip);
		logDao.saveObject(log);
	}

	@Override
	public List<LogDto> findCollectionByCondition(LogDto log, String start, String limit) {
		return logDao.findCollectionByCondition(log, start, limit);
	}

	@Override
	public long countLog(LogDto log) {
		return logDao.countLog(log);
	}
}
