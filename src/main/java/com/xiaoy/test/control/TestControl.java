package com.xiaoy.test.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoy.base.entities.Test;
import com.xiaoy.test.service.TestService;

@Controller
@RequestMapping("/TestControl")
public class TestControl
{
	@Resource
	private TestService testService;

	@RequestMapping("/index")
	public String test()
	{
		List<Test> list = testService.getAllNewFile();
		System.out.println(list);
		return "NewFile";
	}
}
