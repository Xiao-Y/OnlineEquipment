package com.xiaoy.log.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.xiaoy.util.CheckBox;
import com.xiaoy.util.DateHelper;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.PropertyModel;
import com.xiaoy.util.ReadPropertyXML;
import com.xiaoy.util.Tools;

@Controller
@RequestMapping("/log")
public class LogController {
	@Resource
	private LogService logService;
	@Resource
	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "log/index";
	}

	@RequestMapping("/getLogList")
	public @ResponseBody
	JsonResult getLogList(HttpServletRequest request) {
		String userName = Tools.getStringParameter(request, "userName");
		String runClass = Tools.getStringParameter(request, "runClass");
		String operation = Tools.getStringParameter(request, "operation");
		String strCreateTime = Tools.getStringParameter(request, "createTime");
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
			List<Log> list = logService.findCollectionByCondition(log);
			json.setSuccess(true);
			json.setRoot(list);
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
		PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "log", "operation");
		Map<String, Map<String, String>> datas = propertyModel.getDatas();
		Map<String, String> map = datas.get("operation");
		List<CheckBox> list = new ArrayList<CheckBox>();
		for (Map.Entry<String, String> m : map.entrySet()) {
			CheckBox cb = new CheckBox();
			cb.setValueField(m.getKey());
			cb.setDisplayField(m.getValue());
			list.add(cb);
		}
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}
}
