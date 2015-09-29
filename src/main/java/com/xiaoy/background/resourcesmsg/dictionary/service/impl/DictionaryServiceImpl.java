package com.xiaoy.background.resourcesmsg.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Dictionary> getDictionary(Dictionary dictionary) {
		List<Dictionary> list = dictionaryDao.getDictionary(dictionary);
		return list;
	}

	@Override
	public List<Dictionary> getModelNameCheckBox() {
		return dictionaryDao.getModelNameCheckBox();
	}

	@Override
	public List<Dictionary> getFieldNameCheckBox(String modelCode) {
		return dictionaryDao.getFieldNameCheckBox(modelCode);
	}

	@Override
	public void updateDictionary(Dictionary dictionary) {
		dictionaryDao.updateDictionary(dictionary);
	}

	@Override
	public boolean deleteDictionaryIds(List<Dictionary> dictionaryList) {
		if (dictionaryList != null && dictionaryList.size() > 0) {
			List<String> ids = new ArrayList<String>();
			for (Dictionary d : dictionaryList) {
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
	public boolean deleteDictionaryModelOrField(Dictionary dictionary) {
		try {
			dictionaryDao.deleteDictionaryModelOrField(dictionary);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
