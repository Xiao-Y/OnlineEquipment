package com.xiaoy.background.resourcesmsg.notice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.notice.dao.NoticeDao;
import com.xiaoy.background.resourcesmsg.notice.service.NoticeService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Notice;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class NoticeServiceImpl extends CommonServiceImpl<Notice> implements NoticeService {

	private NoticeDao noticeDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Notice> commonDao) {
		this.noticeDao = (NoticeDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public List<Notice> getNoticeList(Notice notice) {
		return noticeDao.getNoticeList(notice);
	}

	@Override
	public long getTotal(Notice notice) {
		return noticeDao.getTotal(notice);
	}
}
