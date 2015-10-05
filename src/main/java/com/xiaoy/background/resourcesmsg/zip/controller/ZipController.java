package com.xiaoy.background.resourcesmsg.zip.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoy.background.resourcesmsg.zip.service.ZipService;
import com.xiaoy.base.entities.Zip;
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
	public void exportZip(HttpServletResponse response) {
		zipService.exportZip(response);
	}

	@ResponseBody
	@RequestMapping(value = "/importZip", method = RequestMethod.POST)
	public JsonResult importZip(MultipartFile multipartFile) {
		JsonResult json = new JsonResult();

		String fileName = multipartFile.getOriginalFilename();
		if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
			json.setSuccess(false);
			json.setMessage(MessageTips.FILE_TYPE_EXECEL);
			return json;
		}

		try {
			zipService.importZip(multipartFile);
			json.setMessage(MessageTips.IMPORT_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMessage(MessageTips.IMPORT_FAILURE);
			e.printStackTrace();
		}
		return json;
	}
}
