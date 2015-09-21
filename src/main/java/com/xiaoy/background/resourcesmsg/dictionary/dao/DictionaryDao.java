package com.xiaoy.background.resourcesmsg.dictionary.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.util.CheckBox;

public interface DictionaryDao extends CommonDao<Dictionary> {

	/**
	 * 根据条件查询出现所能符合条件的数据字典
	 * 
	 * @param dictionary
	 *            查询条件
	 * @return
	 */
	List<Dictionary> getDictionary(Dictionary dictionary);

	/**
	 * 获取模块的下拉列表
	 * 
	 * @param modelName
	 *            模块名称
	 * @param fieldName
	 *            字段名称
	 * @return
	 */
	List<CheckBox> getModelNameCheckBox(String modelName, String fieldName);

}
