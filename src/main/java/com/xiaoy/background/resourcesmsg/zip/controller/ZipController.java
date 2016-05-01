package com.xiaoy.background.resourcesmsg.zip.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoy.annotations.SystemControllerLog;
import com.xiaoy.background.LogParamType;
import com.xiaoy.background.resourcesmsg.zip.service.ZipService;
import com.xiaoy.base.entities.ZipDto;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

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
	public JsonResult getZipList(HttpServletRequest request, ZipDto zip) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");
		String province = Tools.getStringParameter(request, "province");
		String city = Tools.getStringParameter(request, "city");
		String area = Tools.getStringParameter(request, "area");

		if (!StringUtils.isEmpty(area)) {
			zip.setId(area);
		} else if (!StringUtils.isEmpty(city)) {
			zip.setId(city);
		} else if (!StringUtils.isEmpty(province)) {
			zip.setId(province);
		}
		JsonResult json = new JsonResult();
		List<ZipDto> list = zipService.getZipCondition(zip, start, limit);
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
	@SystemControllerLog(module = LogParamType.RESOURCES_MODAL, function = LogParamType.RESOURCES_FUNCTION_ZIP, operation = LogParamType.EXPORT)
	public void exportZip(HttpServletResponse response) {
		zipService.exportZip(response);
	}

	/**
	 * 导入地区信息保存在数据库中
	 * 
	 * @param multipartFile
	 * @return
	 * @date 2015年10月6日 下午2:04:01
	 */
	@ResponseBody
	@RequestMapping(value = "/importZip", method = RequestMethod.POST)
	@SystemControllerLog(module = LogParamType.RESOURCES_MODAL, function = LogParamType.RESOURCES_FUNCTION_ZIP, operation = LogParamType.IMPORT)
	public JsonResult importZip(MultipartFile multipartFile) {
		JsonResult json = new JsonResult();

		String fileName = multipartFile.getOriginalFilename();
		if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
			json.setSuccess(false);
			json.setMessage(MessageTips.FILE_TYPE_EXECEL);
			return json;
		}

		try {
			// 保存信息
			zipService.saveImportZip(multipartFile);
			json.setMessage(MessageTips.IMPORT_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMessage(MessageTips.IMPORT_FAILURE);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 获取省名称（levelType为1的为省直辖市）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getZip")
	public JsonResult getZip(HttpServletRequest request) {
		ZipDto zip = new ZipDto();
		String city = Tools.getStringParameter(request, "city");
		String province = Tools.getStringParameter(request, "province");
		if (!StringUtils.isEmpty(city)) {
			zip.setParentId(city);
		} else if (!StringUtils.isEmpty(province)) {
			zip.setParentId(province);
		} else {
			zip.setLevelType("1");
		}
		JsonResult json = new JsonResult();
		List<ZipDto> list = zipService.getZipCondition(zip, "", "");
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}
}
