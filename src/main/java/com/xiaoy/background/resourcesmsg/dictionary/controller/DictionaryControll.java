package com.xiaoy.background.resourcesmsg.dictionary.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.resourcesmsg.dictionary.service.DictionaryService;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.util.JsonResult;

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

	@ResponseBody
	@RequestMapping("/getDictionary")
	private JsonResult getDictionary(HttpServletRequest request) {
		
		JsonResult json = new JsonResult();
		Dictionary dictionary = new Dictionary();
		List<Dictionary> list = dictionaryService.getDictionary(dictionary);
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}
}
