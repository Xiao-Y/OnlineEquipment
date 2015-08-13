package com.xiaoy.bug.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Bug;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.bug.service.BugService;
import com.xiaoy.menu.service.MenuService;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;

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

	@Resource
	private MenuService menuService;

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

	/**
	 * 通过父id获取其下的子类集合
	 * 
	 * @param menu
	 * @return
	 *
	 * @date 2015年8月13日上午11:04:34
	 */
	@RequestMapping(value = "/parentMenuList")
	public @ResponseBody JsonResult parentMenuList(HttpServletRequest request) {
		Menu menu = new Menu();
		menu.setParentId(Tools.getStringParameter(request, "parentId"));

		List<Menu> menus = menuService.findCollectionByCondition(menu, "", "");
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setRoot(menus);
		return json;
	}

	@RequestMapping(value = "/svaeBug", method = RequestMethod.POST)
	public @ResponseBody JsonResult svaeBug(@RequestBody Bug bug) {
		JsonResult json = new JsonResult();
		bug.setCreateTime(new Date());
		bug.setUpdateTime(new Date());
		try {
			bugService.saveObject(bug);
			json.setSuccess(true);
			json.setMessage("BUG保存成功！");
		} catch (Exception e) {
			json.setMessage("服务器错误！");
			e.printStackTrace();
		}
		return json;
	}
}
