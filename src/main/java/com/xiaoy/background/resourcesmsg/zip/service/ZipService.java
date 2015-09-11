package com.xiaoy.background.resourcesmsg.zip.service;

import java.util.List;

import com.xiaoy.base.entities.Zip;
import com.xiaoy.base.service.CommonService;

/**
 * 省市区服务
 * 
 * @author XiaoY
 * @date: 2015年9月6日 下午11:15:52
 */
public interface ZipService extends CommonService<Zip> {

	/**
	 * 根据条件查询出现省市区信息
	 * 
	 * @param zip
	 * @return
	 */
	List<Zip> getZipCondition(Zip zip);

}
