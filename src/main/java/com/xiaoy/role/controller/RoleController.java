package com.xiaoy.role.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Role;
import com.xiaoy.role.service.RoleService;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;

/**
 * 角色控制器
 * 
 * @author XiaoY
 * @date 2015年8月26日上午11:19:44
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;

	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 *
	 * @date 2015年8月26日上午11:26:01
	 */
	@RequestMapping("/getRoleList")
	public @ResponseBody JsonResult getRoleList(HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");
		
		Role role = new Role();
		JsonResult json = new JsonResult();
		List<Role> roles = roleService.getRoleList(role,start,limit);
		json.setRoot(roles);
		json.setSuccess(true);
		return json;
	}
}
