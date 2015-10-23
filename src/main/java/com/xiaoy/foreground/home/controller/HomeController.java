package com.xiaoy.foreground.home.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoy.background.home.service.HomeService;

@Controller(value = "com.xiaoy.foreground.home.controller.HomeController")
@RequestMapping("/foreground")
public class HomeController {

	@Resource(name = "com.xiaoy.foreground.home.service.impl.HomeServiceImpl")
	private HomeService homeService;

	@RequestMapping("/index")
	public String index(Model model) {
		List<Object> tree = homeService.buildTree();
		model.addAttribute("trees", tree);
		return "foreground/index";
	}
}
