package com.xiaoy.user.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Role;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.entities.Zip;
import com.xiaoy.role.service.RoleService;
import com.xiaoy.user.service.UserService;
import com.xiaoy.util.DateHelper;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;
import com.xiaoy.zip.service.ZipService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	// 全国省市区服务
	@Resource
	private ZipService zipService;
	// 角色服务
	@Resource
	private RoleService roleService;

	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody
	JsonResult getUserList(HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");
		String splie = Tools.getSystemConfigString(request, "zipViewSplit");
		User user = new User();
		JsonResult json = new JsonResult();
		List<User> users = userService.findCollectionByCondition(user, start, limit);
		for (User u : users) {
			StringBuffer str = new StringBuffer("");
			Set<Role> set = u.getRoles();
			for (Role r : set) {
				str.append(r.getRoleName());
				str.append(" || ");
			}
			String[] addresses = u.getAddress().split(splie);
			if (addresses != null && addresses.length > 0) {
				String areaNum = addresses[addresses.length - 1];
				if (!StringUtils.isEmpty(areaNum)) {
					Zip zip = zipService.findObjectById(areaNum);
					u.setAddress(zip.getMergerName());
				} else {
					user.setAddress("");
				}
			}
			String roleNameStr = str.toString();
			if (!StringUtils.isEmpty(roleNameStr) && roleNameStr.endsWith(" || ")) {
				roleNameStr += "||";
				roleNameStr = roleNameStr.replace(" || ||", "");
			}
			u.setRoleName(roleNameStr);
		}
		long total = userService.countByCollection(user);
		json.setRoot(users);
		json.setTotal(total);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 获取省名称（levelType为1的为省直辖市）
	 * 
	 * @return
	 */
	@RequestMapping("/getZip")
	public @ResponseBody
	JsonResult getZip(HttpServletRequest request) {
		Zip zip = new Zip();
		String city = Tools.getStringParameter(request, "city");
		String province = Tools.getStringParameter(request, "province");
		if (!StringUtils.isEmpty(city)) {
			zip.setParentId(city);
		} else if (!StringUtils.isEmpty(province)) {
			zip.setParentId(province);
		} else {
			zip.setLevelType("1");
		}
		JsonResult json = new JsonResult();
		List<Zip> list = zipService.getZipCondition(zip);
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}

	/**
	 * 获取角色
	 * 
	 * @return
	 */
	@RequestMapping("/getRoleList")
	public @ResponseBody
	JsonResult getRoleList() {
		JsonResult json = new JsonResult();
		List<Role> list = roleService.getRoleList();
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	@RequestMapping("/saveUser")
	public @ResponseBody
	JsonResult saveUser(HttpServletRequest request) {
		String username = Tools.getStringParameter(request, "username");
		String password = Tools.getStringParameter(request, "password");
		String birthday = Tools.getStringParameter(request, "birthday");
		String roleId = Tools.getStringParameter(request, "roleId");
		String province = Tools.getStringParameter(request, "province");
		String city = Tools.getStringParameter(request, "city");
		String area = Tools.getStringParameter(request, "area");
		// 从内存中，通过key获取分割符
		String splie = Tools.getSystemConfigString(request, "zipSaveSplit");
		StringBuffer address = new StringBuffer();
		address.append(province);
		address.append(splie);
		address.append(city);
		address.append(splie);
		address.append(area);
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setPassword(password);
		user.setBirthday(birthday == "" ? null : DateHelper.stringConverDate(birthday));
		user.setAddress(address.toString());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setId(roleId);
		roles.add(role);
		user.setRoles(roles);
		JsonResult json = new JsonResult();
		try {
			userService.saveObject(user);
			json.setSuccess(true);
			json.setMessage(MessageTips.SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTips.SERVICE_ERRER);
		}
		return json;
	}
}
