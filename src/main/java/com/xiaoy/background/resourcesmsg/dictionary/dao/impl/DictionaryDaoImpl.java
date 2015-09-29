package com.xiaoy.background.resourcesmsg.dictionary.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.resourcesmsg.dictionary.dao.DictionaryDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.util.CheckBox;

@Repository
public class DictionaryDaoImpl extends CommonDaoImpl<Dictionary> implements DictionaryDao {

	@Override
	public List<Dictionary> getDictionary(Dictionary dictionary) {
		// String start = dictionary.getStart();
		// String limit = dictionary.getLimit();
		StringBuffer hqlWhere = new StringBuffer();
		Map<String, Object> paramsMapValue = this.appendWhere(dictionary, hqlWhere);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, "", "");
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

	@Override
	public List<Dictionary> getFieldNameCheckBox(String modelCode) {
		StringBuffer hql = new StringBuffer("select distinct new Dictionary(fieldName, fieldCode, modelCode) from Dictionary where 1=1 ");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(modelCode)) {
			hql.append(" and modelCode = :modelCode ");
			map.put("modelCode", modelCode);
		}
		List<Dictionary> list = super.find(hql.toString(), map, null, null);
		return list;
	}

	@Override
	public void updateDictionary(Dictionary dictionary) {
		String hql = "update Dictionary set modelName = :modelName, fieldName = :fieldName where modelCode = :modelCode and fieldCode = :fieldCode";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setString("modelName", dictionary.getModelName());
		query.setString("fieldName", dictionary.getFieldName());
		query.setString("modelCode", dictionary.getModelCode());
		query.setString("fieldCode", dictionary.getFieldCode());
		query.executeUpdate();
	}

	@Override
	public void deleteDictionaryIds(List<String> ids) {
		if (ids != null && ids.size() > 0) {
			String where = " and id in (:ids) ";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids);
			super.deleteObjectByCollectionIds(where, map);
		}
	}

	@Override
	public void deleteDictionaryModelOrField(Dictionary dictionary) {
		Map<String, Object> map = new HashMap<String, Object>();

		String modelCode = dictionary.getModelCode();
		String fieldCode = dictionary.getFieldCode();
		StringBuffer where = new StringBuffer();
		if (!StringUtils.isEmpty(modelCode)) {
			where.append(" and modelCode = :modelCode ");
			map.put("modelCode", modelCode);
		}
		if (!StringUtils.isEmpty(fieldCode)) {
			where.append(" and fieldCode = :fieldCode ");
			map.put("fieldCode", fieldCode);
		}
		super.deleteObjectByCollectionIds(where.toString(), map);
	}
}
