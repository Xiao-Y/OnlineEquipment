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
	List<Zip> getZipCondition(Zip zip, String start, String limit);

	/**
	 * 根据条件统计记录数
	 * 
	 * @param zip
	 *            查询条件
	 * @return
	 * @date 2015年10月4日 下午9:06:25
	 */
	long getTotal(Zip zip);
}
