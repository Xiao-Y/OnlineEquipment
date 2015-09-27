package com.xiaoy.background.resourcesmsg.dictionary.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xiaoy.background.resourcesmsg.dictionary.service.DictionaryService;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;

/**
 * 数据字典
 * 
 * @author XiaoY
 * @date: 2015年9月19日 下午9:38:33
 */
@Controller
@RequestMapping("background/resourcesmsg/dictionary")
public class DictionaryControll {

	@Resource
	private DictionaryService dictionaryService;

	@RequestMapping("/index")
	public String index() {
		return "background/resourcesmsg/dictionary/index";
	}

	@ResponseBody
	@RequestMapping("/getDictionary")
	public JsonResult getDictionary(HttpServletRequest request) {
		String limit = Tools.getStringParameter(request, "limit");
		String start = Tools.getStringParameter(request, "start");

		JsonResult json = new JsonResult();
		Dictionary dictionary = new Dictionary();
		dictionary.setLimit(limit);
		dictionary.setStart(start);

		List<Dictionary> list = dictionaryService.getDictionary(dictionary);
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 获取模块的下拉列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getModelNameCheckBox")
	public JsonResult getModelNameCheckBox() {
		JsonResult json = new JsonResult();
		List<Dictionary> list = dictionaryService.getModelNameCheckBox();
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}

	/**
	 * 获取字段的下拉列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFieldNameCheckBox")
	public JsonResult getFieldNameCheckBox(@RequestParam(value = "modelCode", required = false) String modelCode) {
		JsonResult json = new JsonResult();
		List<Dictionary> list = dictionaryService.getFieldNameCheckBox(modelCode);
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}

	@ResponseBody
	@RequestMapping("/getKeyValue")
	public JsonResult getKeyValue(@RequestParam(value = "modelCode", required = false) String modelCode,
			@RequestParam(value = "fieldCode", required = false) String fieldCode) {
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		if (StringUtils.isEmpty(modelCode) || StringUtils.isEmpty(fieldCode)) {
			json.setRoot(null);
			return json;
		} else {
			Dictionary dictionary = new Dictionary();
			dictionary.setModelCode(modelCode);
			dictionary.setFieldCode(fieldCode);
			List<Dictionary> list = dictionaryService.getDictionary(dictionary);
			json.setRoot(list);
		}
		return json;
	}

	/**
	 * 维护数据<br/>
	 * 可以使用createTime来判断是否是新添加的
	 */
	@ResponseBody
	@RequestMapping("/saveDictionary")
	public JsonResult saveDictionary(HttpServletRequest request) {
		String modelCodeBox = Tools.getStringParameter(request, "modelCodeBox");
		String modelCode = Tools.getStringParameter(request, "modelCode");
		String newModelName = Tools.getStringParameter(request, "newModelName");
		String fieldCodeBox = Tools.getStringParameter(request, "fieldCodeBox");
		String fieldCode = Tools.getStringParameter(request, "fieldCode");
		String newFieldName = Tools.getStringParameter(request, "newFieldName");
		String keyValues = Tools.getStringParameter(request, "keyValues");
		List<Dictionary> dictionaryList = JSONArray.parseArray(keyValues, Dictionary.class);
		for (Dictionary d : dictionaryList) {
			System.out.println(d);
		}

		return null;
	}
}
