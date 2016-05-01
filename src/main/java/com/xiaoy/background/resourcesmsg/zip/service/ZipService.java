package com.xiaoy.background.resourcesmsg.zip.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.xiaoy.base.entities.ZipDto;
import com.xiaoy.base.service.CommonService;

/**
 * 省市区服务
 * 
 * @author XiaoY
 * @date: 2015年9月6日 下午11:15:52
 */
public interface ZipService extends CommonService<ZipDto> {

	/**
	 * 根据条件查询出省市区信息
	 * 
	 * @param zip
	 * @return
	 */
	List<ZipDto> getZipCondition(ZipDto zip, String start, String limit);

	/**
	 * 根据条件统计记录数
	 * 
	 * @param zip
	 *            查询条件
	 * @return
	 * @date 2015年10月4日 下午9:06:25
	 */
	long getTotal(ZipDto zip);

	/**
	 * 导出数据
	 * 
	 * @param response
	 * @date 2015年10月5日 下午10:39:36
	 */
	void exportZip(HttpServletResponse response);

	/**
	 * 导入数据
	 * 
	 * @param multipartFile
	 * @date 2015年10月5日 下午10:47:54
	 */
	void saveImportZip(MultipartFile multipartFile);

}
