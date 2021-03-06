package com.xiaoy.background.resourcesmsg.bug.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.bug.dao.BugDao;
import com.xiaoy.background.resourcesmsg.bug.service.BugService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.BugDto;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.util.Tools;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:56
 */
@Service
public class BugServiceImpl extends CommonServiceImpl<BugDto> implements BugService {
	@Resource
	HttpServletRequest request;

	private BugDao bugDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<BugDto> commonDao) {
		this.bugDao = (BugDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public void updateBug(BugDto bug) {
		BugDto b = bugDao.findObjectById(bug.getId());
		b.setBugType(bug.getBugType());
		b.setChildrenId(bug.getChildrenId());
		String imgUrl = bug.getImgUrl();
		if (!StringUtils.isEmpty(imgUrl)) {
			String oldImgUrl = b.getImgUrl();
			// 获取bug图片的路径
			String bugRealPath = Tools.getSystemConfigString(request, "bugRealPath");
			// 删除图片
			Tools.deleteFile(request, oldImgUrl, bugRealPath);
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
	public List<BugDto> findCollectionByCondition(BugDto bug, String start, String limit) {
		List<BugDto> list = bugDao.findCollectionByCondition(bug, start, limit);
		return list;
	}

	@Override
	public long countByCollection(BugDto bug) {
		return bugDao.countByCollection(bug);
	}
}
