package com.xiaoy.background.resourcesmsg.bug.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.resourcesmsg.bug.dao.BugDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.BugDto;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:45
 */
@Repository
public class BugDaoImpl extends CommonDaoImpl<BugDto> implements BugDao
{
	@Override
	public long countByCollection(BugDto bug)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, bug);
		return this.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	@Override
	public List<BugDto> findCollectionByCondition(BugDto bug, String start, String limit)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, bug);
		List<BugDto> list = this.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
		return list;
	}

	/**
	 * 设置查询条件
	 * 
	 * @param hqlWhwere
	 * @param bug
	 * @return
	 * 
	 * @date 2015年8月14日上午9:04:10
	 */
	private Map<String, Object> appHql(StringBuffer hqlWhwere, BugDto bug)
	{
		if (bug != null)
		{
			Map<String, Object> param = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(bug.getBugType()) && !"0".equals(bug.getBugType()))
			{
				hqlWhwere.append(" and bugType = :bugType ");
				param.put("bugType", bug.getBugType());
			}
			if (!StringUtils.isEmpty(bug.getChildrenId()))
			{
				hqlWhwere.append(" and childrenId = :childrenId ");
				param.put("childrenId", bug.getChildrenId());
			}
			if (!StringUtils.isEmpty(bug.getParentId()))
			{
				hqlWhwere.append(" and parentId = :parentId ");
				param.put("parentId", bug.getParentId());
			}
			if (!StringUtils.isEmpty(bug.getReappear()) && !"0".equals(bug.getReappear()))
			{
				hqlWhwere.append(" and reappear = :reappear ");
				param.put("reappear", bug.getReappear());
			}
			if (!StringUtils.isEmpty(bug.getSeverity()) && !"0".equals(bug.getSeverity()))
			{
				hqlWhwere.append(" and severity = :severity ");
				param.put("severity", bug.getSeverity());
			}
			if (!StringUtils.isEmpty(bug.getStatus()) && !"0".equals(bug.getStatus()))
			{
				hqlWhwere.append(" and status = :status ");
				param.put("status", bug.getStatus());
			}
			if (!StringUtils.isEmpty(bug.getTitle()))
			{
				hqlWhwere.append(" and title like :title ");
				param.put("title", "%" + bug.getTitle() + "%");
			}
			hqlWhwere.append(" order by updateTime desc");
			return param;
		}
		return null;
	}
}
