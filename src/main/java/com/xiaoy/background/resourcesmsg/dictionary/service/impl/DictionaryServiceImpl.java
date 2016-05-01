package com.xiaoy.background.resourcesmsg.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.resourcesmsg.dictionary.dao.DictionaryDao;
import com.xiaoy.background.resourcesmsg.dictionary.service.DictionaryService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.DictionaryDto;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class DictionaryServiceImpl extends CommonServiceImpl<DictionaryDto> implements DictionaryService {

	private DictionaryDao dictionaryDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<DictionaryDto> commonDao) {
		super.commonDao = commonDao;
		this.dictionaryDao = (DictionaryDao) commonDao;
	}

	@Override
	public List<DictionaryDto> getDictionary(DictionaryDto dictionary) {
		List<DictionaryDto> list = dictionaryDao.getDictionary(dictionary);
		return list;
	}

	@Override
	public List<DictionaryDto> getModelNameCheckBox() {
		return dictionaryDao.getModelNameCheckBox();
	}

	@Override
	public List<DictionaryDto> getFieldNameCheckBox(String modelCode) {
		return dictionaryDao.getFieldNameCheckBox(modelCode);
	}

	@Override
	public void updateDictionary(DictionaryDto dictionary) {
		dictionaryDao.updateDictionary(dictionary);
	}

	@Override
	public boolean deleteDictionaryIds(List<DictionaryDto> dictionaryList) {
		if (dictionaryList != null && dictionaryList.size() > 0) {
			List<String> ids = new ArrayList<String>();
			for (DictionaryDto d : dictionaryList) {
				ids.add(d.getId());
			}
			try {
				dictionaryDao.deleteDictionaryIds(ids);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean deleteDictionaryModelOrField(DictionaryDto dictionary) {
		try {
			dictionaryDao.deleteDictionaryModelOrField(dictionary);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public long getDictionaryCount(DictionaryDto dictionary) {
		return dictionaryDao.getDictionaryCount(dictionary);
	}
}
