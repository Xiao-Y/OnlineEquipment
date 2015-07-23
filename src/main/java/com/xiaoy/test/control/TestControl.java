package com.xiaoy.test.control;

import java.util.List;

import javax.annotation.Resource;

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
		for (Test t : list)
		{
			System.out.println(t.getId() + " " + t.getName() + " " + t.getSex());
		}
		return "NewFile";
	}

	@RequestMapping("/saveTest")
	public String saveTest()
	{
		Test t = new Test();
		t.setName("XiaoY");
		t.setSex("男");
		testService.saveTest(t);
		return "NewFile";
	}
	
	@RequestMapping("/updateTest")
	public String updateTest()
	{
		Test t = new Test();
		t.setName("XiaoY2");
		t.setSex("男2");
		t.setId(1);
		testService.updateTest(t);
		return "NewFile";
	}
	
	@RequestMapping("/deleteTest")
	public String deleteTest()
	{
		Test t = new Test();
		t.setId(2);;
		testService.deleteTest(t);
		return "NewFile";
	}
}
