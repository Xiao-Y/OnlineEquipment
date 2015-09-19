package com.xiaoy.background.resourcesmsg.dictionary.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.dictionary.dao.DictionaryDao;
import com.xiaoy.background.resourcesmsg.dictionary.service.DictionaryService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class DictionaryServiceImpl extends CommonServiceImpl<Dictionary> implements DictionaryService {

	private DictionaryDao dictionaryDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Dictionary> commonDao) {
		super.commonDao = commonDao;
		this.dictionaryDao = (DictionaryDao) commonDao;
	}

}
