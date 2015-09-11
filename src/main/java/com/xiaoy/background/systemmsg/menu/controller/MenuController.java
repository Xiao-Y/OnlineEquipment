package com.xiaoy.background.systemmsg.menu.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.util.CheckBox;
import com.xiaoy.util.DateHelper;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

/**
 * 菜单操作
 * 
 * @author XiaoY
 * @date: 2015年8月10日 下午9:29:11
 */
@Controller
@RequestMapping("/background/systemmsg/menu")
public class MenuController {
	@Resource
	private MenuService menuService;

	@RequestMapping("/index")
	public String index() {
		return "background/systemmsg/menu/index";
	}

	/**
	 * 菜单列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menuList")
	public @ResponseBody JsonResult menuList(HttpServletRequest request) {
		Menu menu = new Menu();
		menu.setId(Tools.getStringParameter(request, "name"));
		menu.setParentId(Tools.getStringParameter(request, "parentId"));
		menu.setMenuType(Tools.getStringParameter(request, "menuType"));
		menu.setMenuName(Tools.getStringParameter(request, "menuName"));
		menu.setCreateTime(DateHelper.stringConverDate(Tools.getStringParameter(request, "createTime")));
		menu.setUpdateTime(DateHelper.stringConverDate(Tools.getStringParameter(request, "updateTime")));
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");

		List<Menu> menuList = menuService.findCollectionByCondition(menu, start, limit);
		for (Menu m : menuList) {
			if (!"-1".equals(m.getParentId())) {
				Menu mu = menuService.findObjectById(m.getParentId());
				if (mu != null) {
					m.setParentName(mu.getMenuName());
				}
			}
		}
		long total = menuService.countByCollection(menu);
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setRoot(menuList);
		json.setTotal(total);
		return json;
	}

	/**
	 * 保存菜单
	 * 
	 * @param menu
	 * @return
	 * 
	 * @date 2015年8月11日上午11:11:18
	 */
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	public @ResponseBody JsonResult saveMenu(@RequestBody Menu menu) {
		JsonResult json = new JsonResult();
		try {
			// 保存时，添加uuid
			if (StringUtils.isEmpty(menu.getId())) {
				menu.setId(UUID.randomUUID().toString());
			}

			String menuType = menu.getMenuType();
			// 当节点类型选择0即树枝节点，parentId设置为-1为树枝节点类型
			if ("0".equals(menuType)) {
				menu.setParentId("-1");
			}
			// 首次添加保存和更新时间
			menu.setCreateTime(new Date());
			menu.setUpdateTime(new Date());

			menuService.saveObject(menu);
			json.setMessage(MessageTips.SAVE_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 * @return
	 * 
	 * @date 2015年8月11日上午11:14:58
	 */
	@RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
	public @ResponseBody JsonResult updateMenu(@RequestBody Menu menu) {
		JsonResult json = new JsonResult();
		try {
			String menuType = menu.getMenuType();
			// 当节点类型选择0即树枝节点，parentId设置为-1为树枝节点类型
			if ("0".equals(menuType)) {
				menu.setParentId("-1");
			}
			menu.setUpdateTime(new Date());

			menuService.updateMenu(menu);
			json.setMessage(MessageTips.UPDATE_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/deleteMenu/{id}", method = RequestMethod.POST)
	public @ResponseBody JsonResult deleteMenu(@PathVariable("id") String id) {
		JsonResult json = new JsonResult();
		try {
			menuService.deleteObjectByid(id);
			json.setSuccess(true);
			json.setMessage(MessageTips.DELETE_SUCCESS);
		} catch (Exception e) {
			json.setMessage(MessageTips.DELETE_FAILURE);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 向页面发送菜单类型
	 * 
	 * @param request
	 * @return
	 *
	 * @date 2015年9月1日上午11:52:48
	 */
	@RequestMapping("/getMenuType")
	public @ResponseBody JsonResult getMenuType(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		List<CheckBox> checkBox = Tools.getCheckBox(request, "menu", "menuType");
		json.setRoot(checkBox);
		json.setSuccess(true);
		return json;
	}
}