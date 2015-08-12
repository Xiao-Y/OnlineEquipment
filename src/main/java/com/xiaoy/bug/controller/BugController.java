package com.xiaoy.bug.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Bug;
import com.xiaoy.bug.service.BugService;
import com.xiaoy.util.JsonResult;

/**
 * bug控制器类
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:03
 */
@Controller
@RequestMapping("/bug")
public class BugController {

	@Resource
	private BugService bugService;

	@RequestMapping("/index")
	public String index() {
		return "bug/index";
	}

	/**
	 * 获取bug列表
	 * 
	 * @return
	 *
	 * @date 2015年8月12日下午6:00:49
	 */
	@RequestMapping("/getBugList")
	public @ResponseBody JsonResult getBugList() {
		JsonResult json = new JsonResult();
		// hqlWhere
		// paramsMapValue
		try {
			List<Bug> bugs = bugService.findCollectionByCondition("", null);
			json.setSuccess(true);
			json.setRoot(bugs);
		} catch (Exception e) {
			json.setMessage("服务器错误，请稍后重试！");
			e.printStackTrace();
		}
		return json;
	}
}
