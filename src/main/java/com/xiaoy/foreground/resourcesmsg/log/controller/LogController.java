package com.xiaoy.foreground.resourcesmsg.log.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoy.background.resourcesmsg.log.service.LogService;
import com.xiaoy.base.entities.LogDto;

@Controller(value = "com.xiaoy.foreground.resourcesmsg.log.controller.LogController")
@RequestMapping("/foreground/resourcesmsg/log")
public class LogController {

	@Resource
	private LogService logService;

	@RequestMapping("/index")
	public String getLogList(Model model) {
		LogDto log = new LogDto();
		String start = log.getStart();
		String limit = log.getLimit();
		List<LogDto> list = logService.findCollectionByCondition(log, start, limit);
		model.addAttribute("logs", list);
		return "foreground/resourcesmsg/log/logList";
	}

}
