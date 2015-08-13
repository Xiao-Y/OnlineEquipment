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
public class BugServiceImpl extends CommonServiceImpl<Bug> implements BugService
{

	private BugDao bugDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Bug> commonDao)
	{
		this.bugDao = (BugDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public void updateBug(Bug bug)
	{
		Bug b = bugDao.findObjectById(bug.getId());
		b.setBugType(bug.getBugType());
		b.setChildrenId(bug.getChildrenId());
		b.setImgUrl(bug.getImgUrl());
		b.setNote(bug.getNote());
		b.setParentId(bug.getParentId());
		b.setReappear(bug.getReappear());
		b.setReviseExplain(bug.getReviseExplain());
		b.setSeverity(bug.getSeverity());
		b.setStatus(bug.getStatus());
		b.setTitle(bug.getTitle());
		b.setUpdateTime(bug.getUpdateTime());
	}
}
