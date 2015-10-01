package com.xiaoy.background.resourcesmsg.log.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.aop.log.HandleTyepEnum;
import com.xiaoy.background.resourcesmsg.dictionary.controller.DictionaryType;
import com.xiaoy.background.resourcesmsg.log.service.LogService;
import com.xiaoy.background.usermsg.user.service.UserService;
import com.xiaoy.base.entities.Log;
import com.xiaoy.util.CheckBox;
import com.xiaoy.util.DateHelper;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

@Controller
@RequestMapping("/background/resourcesmsg/log")
public class LogController {

	// 工具服务
	@Resource
	private Tools tools;

	@Resource
	private LogService logService;
	@Resource
	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "background/resourcesmsg/log/index";
	}

	@RequestMapping("/getLogList")
	public @ResponseBody
	JsonResult getLogList(HttpServletRequest request) {
		String userName = Tools.getStringParameter(request, "userName");
		String runClass = Tools.getStringParameter(request, "runClass");
		String operation = Tools.getStringParameter(request, "operation");
		String strCreateTime = Tools.getStringParameter(request, "createTime");
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");
		List<Object> objs = null;
		if (!StringUtils.isEmpty(userName)) {
			objs = userService.getUserIdByName(userName);
		}
		Log log = new Log();
		log.setCreateTime(DateHelper.stringConverDate(strCreateTime));
		if (!StringUtils.isEmpty(operation)) {
			for (HandleTyepEnum h : HandleTyepEnum.values()) {
				if (h.getName().equals(operation)) {
					log.setOperation(h.getValue());
				}
			}
		}
		log.setRunClass(runClass);
		log.setUserIds(objs);

		JsonResult json = new JsonResult();
		try {
			List<Log> list = logService.findCollectionByCondition(log, start, limit);
			long total = logService.countLog(log);
			json.setSuccess(true);
			json.setRoot(list);
			json.setTotal(total);
		} catch (Exception e) {
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 向页面发送操作类型下拉列表中的数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getHandleType")
	public @ResponseBody
	JsonResult getHandleType(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "log", "operation");
		List<CheckBox> list = tools.getCheckBox(DictionaryType.LOG_MODEL_CODE_LOG, DictionaryType.LOG_FIELD_CODE_OPERATION);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}
}
