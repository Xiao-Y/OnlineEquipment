package com.xiaoy.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController
{
	@RequestMapping("/index")
	public String index()
	{
		return "user/index";
	}

	@RequestMapping("/getUserList")
	public @ResponseBody
	JsonResult getUserList(HttpServletRequest request)
	{
		JsonResult json = new JsonResult();
		return json;
	}
}
