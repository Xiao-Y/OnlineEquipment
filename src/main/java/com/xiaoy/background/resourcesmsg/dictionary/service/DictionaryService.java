package com.xiaoy.background.resourcesmsg.dictionary.service;

import java.util.List;

import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.base.service.CommonService;

public interface DictionaryService extends CommonService<Dictionary> {

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
	 * @return
	 */
	List<Dictionary> getModelNameCheckBox();

	/**
	 * 通过模块Code获取字段的下拉列表
	 * 
	 * @param modelCode
	 *            模块Code
	 * @return
	 */
	List<Dictionary> getFieldNameCheckBox(String modelCode);

	/**
	 * 更新数据字典的模块和字段
	 * 
	 * @param dictionary
	 * <br/>
	 *            更新模块名和Code<br/>
	 *            更新字段名和Code<br/>
	 */
	void updateDictionary(Dictionary dictionary);

}
