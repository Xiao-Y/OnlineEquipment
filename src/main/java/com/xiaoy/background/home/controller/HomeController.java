package com.xiaoy.background.home.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.home.service.HomeService;
import com.xiaoy.background.resourcesmsg.dictionary.controller.DictionaryType;
import com.xiaoy.util.CheckBox;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;

@Controller(value = "com.xiaoy.background.home.controller.HomeController")
@RequestMapping("/background/home")
public class HomeController {

	@Resource(name = "com.xiaoy.background.home.service.impl.HomeServiceImpl")
	private HomeService homeService;
	// 工具服务
	@Resource
	private Tools tools;

	/**
	 * 进入主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String home() {
		return "background/home/index";
	}

	/**
	 * 构建菜单树
	 * 
	 * @return
	 */
	@RequestMapping("/buildTree")
	public @ResponseBody
	List<Object> buildTree() {
		return homeService.buildTree();
	}

	/**
	 * 向页面发送主题下拉列表中的数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTheme")
	public @ResponseBody
	JsonResult getTheme(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "home", "theme");
		List<CheckBox> list = tools.getCheckBox(DictionaryType.HOME_MODEL_CODE_HOME, DictionaryType.HOME_FIELD_CODE_THEME);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}
}
