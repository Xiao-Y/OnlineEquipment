package com.xiaoy.home.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.home.service.HomeService;
import com.xiaoy.util.CheckBox;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;

@Controller
@RequestMapping("/home")
public class HomeController
{

	@Resource
	private HomeService homeService;

	/**
	 * 进入主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String home()
	{
		return "home/index";
	}

	/**
	 * 构建菜单树
	 * 
	 * @return
	 */
	@RequestMapping("/buildTree")
	public @ResponseBody List<Object> buildTree()
	{
		return homeService.buildTree();
	}
	
	/**
	 * 向页面发送主题下拉列表中的数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTheme")
	public @ResponseBody JsonResult getTheme(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		List<CheckBox> list = Tools.getCheckBox(request, "home", "theme");
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}
}
