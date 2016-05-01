package com.xiaoy.background.resourcesmsg.dictionary.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.DictionaryDto;
import com.xiaoy.util.CheckBox;

public interface DictionaryDao extends CommonDao<DictionaryDto> {

	/**
	 * 根据条件查询出现所能符合条件的数据字典
	 * 
	 * @param dictionary
	 *            查询条件
	 * @return
	 */
	List<DictionaryDto> getDictionary(DictionaryDto dictionary);

	/**
	 * 获取下拉列表
	 * 
	 * @param dictionary
	 *            模块名，字段名
	 * @return
	 */
	List<CheckBox> getCheckBox(DictionaryDto dictionary);

	/**
	 * 获取数据字典模块的下拉列表
	 * 
	 * @return
	 */
	List<DictionaryDto> getModelNameCheckBox();

	/**
	 * 通过模块Code获取字段的下拉列表
	 * 
	 * @param modelCode
	 *            模块Code
	 * @return
	 */
	List<DictionaryDto> getFieldNameCheckBox(String modelCode);

	/**
	 * 更新数据字典的模块和字段
	 * 
	 * @param dictionary
	 * <br/>
	 *            更新模块名和Code<br/>
	 *            更新字段名和Code<br/>
	 */
	void updateDictionary(DictionaryDto dictionary);

	/**
	 * 根据一组id删除对应对象
	 * 
	 * @param dictionaryList
	 */
	void deleteDictionaryIds(List<String> ids);

	/**
	 * 跟根据模块codet和fieldCode删除数据字典
	 * 
	 * @param modelCodeBox
	 *            模块code
	 * @param fieldCodeBox
	 *            字段code
	 * @return
	 */
	void deleteDictionaryModelOrField(DictionaryDto dictionary);

	/**
	 * 根据条件统计出现记录数
	 * 
	 * @param dictionary
	 * @return
	 */
	long getDictionaryCount(DictionaryDto dictionary);
}
