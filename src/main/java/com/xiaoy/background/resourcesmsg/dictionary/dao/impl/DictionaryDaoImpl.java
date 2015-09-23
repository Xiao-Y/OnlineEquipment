package com.xiaoy.background.resourcesmsg.dictionary.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.resourcesmsg.dictionary.dao.DictionaryDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.util.CheckBox;

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
		if (dictionary != null) {
			Map<String, Object> map = new HashMap<>();
			if (!StringUtils.isEmpty(dictionary.getModelCode())) {
				hqlWhere.append(" and modelCode = :modelCode");
				map.put("modelCode", dictionary.getModelCode());
			}

			if (!StringUtils.isEmpty(dictionary.getFieldCode())) {
				hqlWhere.append(" and fieldCode = :fieldCode");
				map.put("fieldCode", dictionary.getFieldCode());
			}

			return map;
		}
		return null;
	}

	@Override
	public List<CheckBox> getCheckBox(Dictionary dictionary) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> map = this.appendWhere(dictionary, hqlWhere);
		List<Dictionary> list = super.findCollectionByCondition(hqlWhere.toString(), map);
		if (list != null && list.size() > 0) {
			List<CheckBox> checkList = new ArrayList<CheckBox>();
			for (Dictionary d : list) {
				CheckBox box = new CheckBox();
				box.setDisplayField(d.getDisplayField());
				box.setValueField(d.getValueField());
				checkList.add(box);
			}
			return checkList;
		}
		return null;
	}

	@Override
	public List<Dictionary> getModelNameCheckBox() {
		String hql = "select distinct new Dictionary(modelName, modelCode) from Dictionary";
		List<Dictionary> list = super.find(hql, null, null, null);
		return list;
	}

}
