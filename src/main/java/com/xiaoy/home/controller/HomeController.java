package com.xiaoy.home.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.home.service.HomeService;

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
	@ResponseBody
	@RequestMapping("/buildTree")
	public List<Object> buildTree()
	{
		return homeService.buildTree();
	}
}
