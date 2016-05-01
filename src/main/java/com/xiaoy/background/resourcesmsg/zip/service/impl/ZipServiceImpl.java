package com.xiaoy.background.resourcesmsg.zip.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoy.background.resourcesmsg.zip.dao.ZipDao;
import com.xiaoy.background.resourcesmsg.zip.service.ZipService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.ZipDto;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.util.excel.poi.PoiDBExportToExcelFile;
import com.xiaoy.util.excel.poi.PoiExcelFileExportToDB;

@Service
public class ZipServiceImpl extends CommonServiceImpl<ZipDto> implements ZipService {

	private ZipDao zipDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<ZipDto> commonDao) {
		this.zipDao = (ZipDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public List<ZipDto> getZipCondition(ZipDto zip, String start, String limit) {
		return zipDao.getZipCondition(zip, start, limit);
	}

	@Override
	public long getTotal(ZipDto zip) {
		return zipDao.getTotal(zip);
	}

	@Override
	public void exportZip(HttpServletResponse response) {
		// 设置表头
		ArrayList<String> fieldName = this.getExcelFieldNameList();
		// 装载要导出数据
		ArrayList<List<String>> fieldData = this.getExcelFieldDataList();
		try {
			// 输出文件的名称
			String fileName = "全国省市信息.xlsx";
			PoiDBExportToExcelFile ex = new PoiDBExportToExcelFile("全国省市信息", fieldName, fieldData);
			// 导出数据到Excle
			ex.expordExcel(response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置表头
	 * 
	 * @return
	 */
	private ArrayList<String> getExcelFieldNameList() {
		String[] str = { "行政码", "全称", "上级行政码", "简称", "等级", "城市CODE", "邮编", "完整名称", "经度", "纬度", "名称拼音" };
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		System.out.println("表头设置完成...");
		return list;
	}

	/**
	 * 装载要导出数据
	 * 
	 * @param elecUserForm
	 * @return
	 */
	private ArrayList<List<String>> getExcelFieldDataList() {
		List<ZipDto> zips = this.getZipCondition(null, "", "");
		ArrayList<List<String>> fieldData = null;
		if (zips != null && zips.size() > 0) {
			fieldData = new ArrayList<List<String>>();
			for (ZipDto zip : zips) {
				ArrayList<String> dataList = new ArrayList<String>();
				// id,name,parent_Id,short_name,level_Type,city_code,zip_code,merger_name,lng,lat,pinyin
				dataList.add(zip.getId());
				dataList.add(zip.getName());
				dataList.add(zip.getParentId());
				dataList.add(zip.getShortName());
				dataList.add(zip.getLevelType());
				dataList.add(zip.getCityCode());
				dataList.add(zip.getZipCode());
				dataList.add(zip.getMergerName());
				dataList.add(zip.getLng());
				dataList.add(zip.getLat());
				dataList.add(zip.getPinyin());

				fieldData.add(dataList);
			}
		}
		System.out.println("获取数据集完成...");
		return fieldData;
	}

	@Override
	public void saveImportZip(MultipartFile multipartFile) {
		// obj转换成zip对象
		List<ZipDto> zips = this.ListObjToListZip(multipartFile);
		// 选删除原数据
		zipDao.deleteAll();
		// 添加新数据
		zipDao.saveObjectCollection(zips);
	}

	/**
	 * obj转换成zip对象
	 * 
	 * @param multipartFile
	 * @return
	 * @date 2015年10月5日 下午11:00:18
	 */
	private List<ZipDto> ListObjToListZip(MultipartFile multipartFile) {
		List<ZipDto> zipList = new ArrayList<ZipDto>();
		try {
			PoiExcelFileExportToDB ef = new PoiExcelFileExportToDB(multipartFile, 1, 0);
			List<Object[]> dataList = ef.getDataList();
			// id,name,parent_Id,short_name,level_Type,city_code,zip_code,merger_name,lng,lat,pinyin
			for (Object[] objs : dataList) {
				ZipDto zip = new ZipDto();
				zip.setId((String) objs[0]);
				zip.setName((String) objs[1]);
				zip.setParentId((String) objs[2]);
				zip.setShortName((String) objs[3]);
				zip.setLevelType((String) objs[4]);
				zip.setCityCode((String) objs[5]);
				zip.setZipCode((String) objs[6]);
				zip.setMergerName((String) objs[7]);
				zip.setLng((String) objs[8]);
				zip.setLat((String) objs[9]);
				zip.setPinyin((String) objs[10]);

				zipList.add(zip);
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zipList;
	}
}
