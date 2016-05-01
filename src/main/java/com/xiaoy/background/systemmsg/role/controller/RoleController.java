package com.xiaoy.background.systemmsg.role.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.annotations.SystemControllerLog;
import com.xiaoy.background.LogParamType;
import com.xiaoy.background.systemmsg.role.service.RoleService;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.entities.Role;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

/**
 * 角色控制器
 * 
 * @author XiaoY
 * @date 2015年8月26日上午11:19:44
 */
@Controller
@RequestMapping("/background/systemmsg/role")
public class RoleController {

	@Resource
	private RoleService roleService;

	@RequestMapping("/index")
	public String index() {
		return "background/systemmsg/role/index";
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 * 
	 * @date 2015年8月26日上午11:26:01
	 */
	@RequestMapping("/getRoleList")
	public @ResponseBody
	JsonResult getRoleList(HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");
		Role role = new Role();
		JsonResult json = new JsonResult();
		List<Role> roles = roleService.getRoleList(request, role, start, limit);
		json.setRoot(roles);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 构建菜单树
	 * 
	 * @return
	 * 
	 * @date 2015年8月27日下午6:02:37
	 */
	@RequestMapping("/buildTree")
	public @ResponseBody
	List<Object> buildTree() {
		return roleService.buildTree();
	}

	/**
	 * 保存角色信息
	 * 
	 * @param role
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午12:12:52
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRole")
	@SystemControllerLog(module = LogParamType.SYSTEM_MODAL, function = LogParamType.SYSTEM_FUNCTION_ROLE, operation = LogParamType.ADD)
	public JsonResult saveRole(Role role) {
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		try {
			role.setId(UUID.randomUUID().toString());
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			// 授权状态，0未授权，1已授权
			role.setAuthorizeStatus("0");
			roleService.saveObject(role);
			json.setMessage(MessageTips.SAVE_SUCCESS);
		} catch (Exception e) {
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 保存权限信息
	 * 
	 * @param request
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午11:25:49
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRoleAuthorize")
	public JsonResult saveRoleAuthorize(HttpServletRequest request,Model menu) {
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		return json;
	}
}
