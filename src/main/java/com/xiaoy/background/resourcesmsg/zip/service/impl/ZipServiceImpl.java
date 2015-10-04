package com.xiaoy.background.resourcesmsg.zip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.zip.dao.ZipDao;
import com.xiaoy.background.resourcesmsg.zip.service.ZipService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Zip;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class ZipServiceImpl extends CommonServiceImpl<Zip> implements ZipService {

	private ZipDao zipDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Zip> commonDao) {
		this.zipDao = (ZipDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public List<Zip> getZipCondition(Zip zip, String start, String limit) {
		return zipDao.getZipCondition(zip, start, limit);
	}

	@Override
	public long getTotal(Zip zip) {
		return zipDao.getTotal(zip);
	}
}
