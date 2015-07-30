package com.xiaoy.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@RequestMapping("/index")
	public String home() {
		System.out.println("home/index");
		return "home/index";
	}
}
