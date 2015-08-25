package com.xiaoy.bug.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Bug;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.bug.dao.BugDao;
import com.xiaoy.bug.service.BugService;
import com.xiaoy.util.Tools;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:56
 */
@Service
public class BugServiceImpl extends CommonServiceImpl<Bug> implements BugService
{
	/**
	 * 系统配置文件
	 */
	public final static String SYSTEM_CONFIG = "systemConfig.properties";
	
	@Resource
	HttpServletRequest request;
	
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
		//获取bug图片的路径 
		String bugRealPath = Tools.getReadPropertiesString(SYSTEM_CONFIG, "bugRealPath");
		
		Bug b = bugDao.findObjectById(bug.getId());
		b.setBugType(bug.getBugType());
		b.setChildrenId(bug.getChildrenId());
		String imgUrl = bug.getImgUrl();
		if (!StringUtils.isEmpty(imgUrl))
		{
			String oldImgUrl = b.getImgUrl();
			//删除图片
			Tools.deleteFile(request, oldImgUrl,bugRealPath);
			b.setImgUrl(imgUrl);
		}
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
		List<Bug> list = bugDao.findCollectionByCondition(bug, start, limit);
		return list;
	}

	@Override
	public long countByCollection(Bug bug)
	{
		return bugDao.countByCollection(bug);
	}
}
