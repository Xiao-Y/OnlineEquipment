package com.xiaoy.background.resourcesmsg.zip.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Zip;

/**
 * 省市区Dao
 * 
 * @author XiaoY
 * @date: 2015年9月6日 下午11:16:12
 */
public interface ZipDao extends CommonDao<Zip> {

	/**
	 * 根据条件查询出现省市区信息
	 * 
	 * @param zip
	 * @return
	 */
	List<Zip> getZipCondition(Zip zip);

}
