package com.xiaoy.background.resourcesmsg.dictionary.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoy.background.resourcesmsg.dictionary.dao.DictionaryDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Dictionary;

@Repository
public class DictionaryDaoImpl extends CommonDaoImpl<Dictionary> implements DictionaryDao {

	@Override
	public List<Dictionary> getDictionary(Dictionary dictionary) {
		String start = dictionary.getStart();
		String limit = dictionary.getLimit();
		StringBuffer hqlWhere = new StringBuffer();
		Map<String, Object> paramsMapValue = this.appendWhere(dictionary, hqlWhere);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
	}

	/**
	 * 拼接查询条件
	 * 
	 * @param dictionary
	 *            查询参数
	 * @param hqlWhere
	 *            查询条件
	 * @return
	 */
	private Map<String, Object> appendWhere(Dictionary dictionary, StringBuffer hqlWhere) {
		// TODO Auto-generated method stub
		return null;
	}

}
