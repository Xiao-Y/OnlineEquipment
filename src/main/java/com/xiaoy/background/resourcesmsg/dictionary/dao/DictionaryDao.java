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
	 * 获取下拉列表
	 * 
	 * @param dictionary
	 *            模块名，字段名
	 * @return
	 */
	List<CheckBox> getCheckBox(Dictionary dictionary);

	/**
	 * 获取数据字典模块的下拉列表
	 * 
	 * @return
	 */
	List<Dictionary> getModelNameCheckBox();

}
