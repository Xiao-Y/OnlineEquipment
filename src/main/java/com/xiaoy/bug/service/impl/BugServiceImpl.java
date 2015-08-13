package com.xiaoy.bug.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public List<Bug> findCollectionByCondition(Bug bug, String start, String limit)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, bug);
		List<Bug> list = bugDao.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
		return list;
	}

	private Map<String, Object> appHql(StringBuffer hqlWhwere, Bug bug)
	{
		if (bug != null)
		{
			Map<String, Object> param = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(bug.getBugType()))
			{
				hqlWhwere.append(" and bugType = :bugType ");
				param.put("bugType", bug.getBugType());
			}
			if (!StringUtils.isEmpty(bug.getChildrenId()))
			{
				hqlWhwere.append(" and id = :id ");
				param.put("id", bug.getChildrenId());
			}
			if (!StringUtils.isEmpty(bug.getParentId()))
			{
				hqlWhwere.append(" and parentId = :parentId ");
				param.put("parentId", bug.getParentId());
			}
			if (!StringUtils.isEmpty(bug.getReappear()))
			{
				hqlWhwere.append(" and reappear = :reappear ");
				param.put("reappear", bug.getReappear());
			}
			if (!StringUtils.isEmpty(bug.getSeverity()))
			{
				hqlWhwere.append(" and severity = :severity ");
				param.put("severity", bug.getSeverity());
			}
			if (!StringUtils.isEmpty(bug.getStatus()))
			{
				hqlWhwere.append(" and status = :status ");
				param.put("status", bug.getStatus());
			}
			if (!StringUtils.isEmpty(bug.getTitle()))
			{
				hqlWhwere.append(" and title like :title ");
				param.put("title", "%" + bug.getTitle() + "%");
			}
			return param;
		}
		return null;
	}
}
