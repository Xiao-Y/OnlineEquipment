package com.xiaoy.background.resourcesmsg.notice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.notice.dao.NoticeDao;
import com.xiaoy.background.resourcesmsg.notice.service.NoticeService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.NoticeDto;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class NoticeServiceImpl extends CommonServiceImpl<NoticeDto> implements NoticeService {

	private NoticeDao noticeDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<NoticeDto> commonDao) {
		this.noticeDao = (NoticeDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public List<NoticeDto> getNoticeList(NoticeDto notice) {
		return noticeDao.getNoticeList(notice);
	}

	@Override
	public long getTotal(NoticeDto notice) {
		return noticeDao.getTotal(notice);
	}
}
