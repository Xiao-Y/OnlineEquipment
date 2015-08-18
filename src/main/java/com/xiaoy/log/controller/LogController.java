package com.xiaoy.log.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Log;
import com.xiaoy.log.service.LogService;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;

@Controller
@RequestMapping("/log")
public class LogController
{
	@Resource
	private LogService logService;

	@RequestMapping("/index")
	public String index()
	{
		return "log/index";
	}

	@RequestMapping("/getLogList")
	public @ResponseBody
	JsonResult getLogList(HttpServletRequest request)
	{
		Log log = new Log();
		JsonResult json = new JsonResult();
		try
		{
			List<Log> list = logService.findCollectionByCondition(log);
			json.setSuccess(true);
			json.setRoot(list);
		} catch (Exception e)
		{
			json.setMessage(MessageTips.ERRER);
			e.printStackTrace();
		}
		return json;
	}
}
