package com.xiaoy.background.resourcesmsg.dictionary.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.resourcesmsg.dictionary.service.DictionaryService;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.util.CheckBox;
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
	public List<CheckBox> getModelNameCheckBox() {
		//List<CheckBox> list = dictionaryService.getModelNameCheckBox("dic");
		return null;
	}
}
