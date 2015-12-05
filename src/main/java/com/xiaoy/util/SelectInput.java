package com.xiaoy.util;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面下拉列表加载数据
 * 
 * @author XiaoY
 * @date: 2015年12月5日 下午10:35:30
 */
@Controller
@RequestMapping("/common")
public class SelectInput {

	// 工具服务
	@Resource
	private Tools tools;

	/**
	 * 通过模块code和字段code获取数据字典集合
	 * 
	 * @param request
	 * @return
	 * @date 2015年12月5日 下午10:52:26
	 */
	@ResponseBody
	@RequestMapping(value = "/getSelect", method = RequestMethod.GET)
	public List<CheckBox> getSeverity(HttpServletRequest request) {
		String modelCode = Tools.getStringParameter(request, "modelCode");
		String fieldCode = Tools.getStringParameter(request, "fieldCode");
		List<CheckBox> list = tools.getCheckBox(modelCode, fieldCode);
		Collections.sort(list, new MySortASC());
		return list;
	}
}
