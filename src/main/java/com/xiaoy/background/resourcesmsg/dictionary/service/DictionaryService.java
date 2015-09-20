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

}
