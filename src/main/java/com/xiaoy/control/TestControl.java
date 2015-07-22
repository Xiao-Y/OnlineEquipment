package com.xiaoy.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TestControl")
public class TestControl
{

	@RequestMapping("/index")
	public String test()
	{
		return "NewFile";
	}
}
