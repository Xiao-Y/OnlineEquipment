package com.xiaoy.bug.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Bug;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.bug.dao.BugDao;
import com.xiaoy.bug.service.BugService;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:56
 */
@Service
public class BugServiceImpl extends CommonServiceImpl<Bug> implements BugService {

	@SuppressWarnings("unused")
	private BugDao bugDao;
	
	@Resource
	@Override
	public void setCommonDao(CommonDao<Bug> commonDao) {
		this.bugDao = (BugDao) commonDao;
		super.commonDao = commonDao;
	}
}
