package com.xiaoy.zip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Zip;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.zip.dao.ZipDao;
import com.xiaoy.zip.service.ZipService;

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
	public List<Zip> getZipCondition(Zip zip) {
		return zipDao.getZipCondition(zip);
	}
}
