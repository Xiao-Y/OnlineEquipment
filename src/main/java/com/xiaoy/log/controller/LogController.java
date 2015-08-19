package com.xiaoy.log.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.aop.log.HandleTyepEnum;
import com.xiaoy.base.entities.Log;
import com.xiaoy.log.service.LogService;
import com.xiaoy.user.service.UserService;
import com.xiaoy.util.DateHelper;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

@Controller
@RequestMapping("/log")
public class LogController
{
	@Resource
	private LogService logService;
	@Resource
	private UserService userService;

	@RequestMapping("/index")
	public String index()
	{
		return "log/index";
	}

	@RequestMapping("/getLogList")
	public @ResponseBody
	JsonResult getLogList(HttpServletRequest request)
	{
		String userName = Tools.getStringParameter(request, "userName");
		String runClass = Tools.getStringParameter(request, "runClass");
		String operation = Tools.getStringParameter(request, "operation");
		String strCreateTime = Tools.getStringParameter(request, "createTime");
		List<Object> objs = null;
		if (!StringUtils.isEmpty(userName))
		{
			objs = userService.getUserIdByName(userName);
		}
		Log log = new Log();
		log.setCreateTime(DateHelper.stringConverDate(strCreateTime));
		if (!StringUtils.isEmpty(operation))
		{
			for (HandleTyepEnum h : HandleTyepEnum.values())
			{
				if (h.getName().equals(operation))
				{
					log.setOperation(h.getValue());
				}
			}
		}
		log.setRunClass(runClass);
		log.setUserIds(objs);

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
