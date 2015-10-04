package com.xiaoy.background.resourcesmsg.zip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.resourcesmsg.zip.service.ZipService;
import com.xiaoy.base.entities.Zip;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;
import com.xiaoy.util.excel.poi.PoiDBExportToExcelFile;

/**
 * 地区管理
 * 
 * @author XiaoY
 * @date: 2015年10月4日 下午8:43:13
 */
@Controller
@RequestMapping("/background/resourcesmsg/zip")
public class ZipController {

	@Resource
	private ZipService zipService;

	@RequestMapping("/index")
	public String index() {
		return "background/resourcesmsg/zip/index";
	}

	@ResponseBody
	@RequestMapping("/getZipList")
	public JsonResult getZipList(HttpServletRequest request, Zip zip) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");

		JsonResult json = new JsonResult();
		List<Zip> list = zipService.getZipCondition(zip, start, limit);
		long total = zipService.getTotal(zip);
		json.setRoot(list);
		json.setTotal(total);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 导出地区Excel表格
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 * @date 2015年10月4日 下午9:32:50
	 */
	@RequestMapping(value = "/exportZip", method = RequestMethod.GET)
	public void exportZip(HttpServletResponse response) throws IOException {
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
		String[] str = { "id", "Name", "ParentId", "ShortName", "LevelType", "CityCode", "ZipCode", "MergerName", "lng", "Lat", "Pinyin" };
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
	public ArrayList<List<String>> getExcelFieldDataList() {
		List<Zip> zips = zipService.getZipCondition(null, "", "");
		ArrayList<List<String>> fieldData = null;
		if (zips != null && zips.size() > 0) {
			fieldData = new ArrayList<List<String>>();
			for (Zip zip : zips) {
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
}
