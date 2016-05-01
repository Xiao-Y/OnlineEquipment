package com.xiaoy.background.usermsg.user.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.annotations.SystemControllerLog;
import com.xiaoy.background.LogParamType;
import com.xiaoy.background.resourcesmsg.zip.service.ZipService;
import com.xiaoy.background.systemmsg.role.service.RoleService;
import com.xiaoy.background.usermsg.user.service.UserService;
import com.xiaoy.base.entities.RoleDto;
import com.xiaoy.base.entities.UserDto;
import com.xiaoy.base.entities.ZipDto;
import com.xiaoy.base.entities.base.Role;
import com.xiaoy.util.DateHelper;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

@Controller
@RequestMapping("/background/usermsg/user")
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
		return "background/usermsg/user/index";
	}

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserList")
	public JsonResult getUserList(HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");
		String username = Tools.getStringParameter(request, "username");
		String birthday = Tools.getStringParameter(request, "birthday");
		String[] roleIdStr = request.getParameterValues("roleId");
		String province = Tools.getStringParameter(request, "province");
		String city = Tools.getStringParameter(request, "city");
		String area = Tools.getStringParameter(request, "area");
		String createTime = Tools.getStringParameter(request, "createTime");
		String updateTime = Tools.getStringParameter(request, "updateTime");
		String zipSaveSplit = Tools.getSystemConfigString(request, "zipSaveSplit");
		StringBuffer address = new StringBuffer("");
		if (!StringUtils.isEmpty(province)) {
			address.append(province);
			address.append(zipSaveSplit);
		}
		if (!StringUtils.isEmpty(city)) {
			address.append(city);
			address.append(zipSaveSplit);
		}
		if (!StringUtils.isEmpty(area)) {
			address.append(area);
			address.append(zipSaveSplit);
		}
		UserDto user = new UserDto();
		user.setUsername(username);
		user.setBirthday(birthday == "" ? null : DateHelper.stringConverDate(birthday));
		user.setCreateTime(createTime == "" ? null : DateHelper.stringConverDate(createTime));
		user.setUpdateTime(updateTime == "" ? null : DateHelper.stringConverDate(updateTime));
		if (!StringUtils.isEmpty(address) && address.toString().endsWith(zipSaveSplit)) {
			address.append(zipSaveSplit);
			String addressStr = address.toString().replace(zipSaveSplit + zipSaveSplit, "");
			user.setAddress(addressStr);
		}
		if (roleIdStr != null && roleIdStr.length > 0 && roleIdStr[0] != "") {
			Set<RoleDto> roles = new HashSet<RoleDto>();
			for (String roleId : roleIdStr) {
				RoleDto role = new RoleDto();
				role.setId(roleId);
				roles.add(role);
			}
			user.setRoles(roles);
		}

		String splie = Tools.getSystemConfigString(request, "zipViewSplit");
		List<UserDto> users = userService.findCollectionByCondition(user, start, limit);
		for (UserDto u : users) {
			StringBuffer str = new StringBuffer("");
			Set<RoleDto> set = u.getRoles();
			String[] roleIds = new String[set.size()];
			int i = 0;
			for (Role r : set) {
				roleIds[i] = r.getId();
				// 遍历角色名称，拼接用于显示
				str.append(r.getRoleName());
				str.append(" || ");
				i++;
			}
			// 添加角色id
			u.setRoleId(roleIds);
			String[] addresses = u.getAddress().split(splie);
			if (addresses != null && addresses.length > 0) {
				// 添加地理代码
				u.setProvince(addresses[0]);
				u.setCity(addresses[1]);
				u.setArea(addresses[2]);
				// 用于页面显示地理信息
				String areaNum = addresses[addresses.length - 1];
				if (!StringUtils.isEmpty(areaNum)) {
					ZipDto zip = zipService.findObjectById(areaNum);
					u.setAddress(zip.getMergerName());

				} else {
					u.setAddress("");
				}
			}
			// 拼接用于显示的角色名称
			String roleNameStr = str.toString();
			if (!StringUtils.isEmpty(roleNameStr) && roleNameStr.endsWith(" || ")) {
				roleNameStr += "||";
				roleNameStr = roleNameStr.replace(" || ||", "");
			}
			u.setRoleName(roleNameStr);
		}
		long total = userService.countByCollection(user);
		JsonResult json = new JsonResult();
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
		ZipDto zip = new ZipDto();
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
		List<ZipDto> list = zipService.getZipCondition(zip, "", "");
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
		List<RoleDto> list = roleService.getRoleList();
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveUser")
	@SystemControllerLog(module = LogParamType.SYSTEM_MODAL, function = LogParamType.SYSTEM_FUNCTION_USER, operation = LogParamType.ADD)
	public JsonResult saveUser(HttpServletRequest request) {
		String username = Tools.getStringParameter(request, "username");
		String password = Tools.getStringParameter(request, "password");
		String birthday = Tools.getStringParameter(request, "birthday");
		// String roleId = Tools.getStringParameter(request, "roleId");
		String roleIds[] = request.getParameterValues("roleId");
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
		UserDto user = new UserDto();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setPassword(password);
		user.setBirthday(birthday == "" ? null : DateHelper.stringConverDate(birthday));
		user.setAddress(address.toString());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		Set<RoleDto> roles = new HashSet<RoleDto>();
		for (String roleId : roleIds) {
			RoleDto role = new RoleDto();
			role.setId(roleId);
			roles.add(role);
		}
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

	/**
	 * 通过userId删除用户信息，同时删除中间表关联关系
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserById/{id}", method = RequestMethod.POST)
	@SystemControllerLog(module = LogParamType.SYSTEM_MODAL, function = LogParamType.SYSTEM_FUNCTION_USER, operation = LogParamType.DELETE)
	public JsonResult deleteUserById(@PathVariable("id") String id) {
		JsonResult json = new JsonResult();
		try {
			userService.deleteUserById(id);
			json.setSuccess(true);
			json.setMessage(MessageTips.DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTips.DELETE_FAILURE);
		}
		return json;
	}

	/**
	 * 更新信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@SystemControllerLog(module = LogParamType.SYSTEM_MODAL, function = LogParamType.SYSTEM_FUNCTION_USER, operation = LogParamType.UPDATE)
	public JsonResult updateUser(HttpServletRequest request) {
		String username = Tools.getStringParameter(request, "username");
		String id = Tools.getStringParameter(request, "id");
		String password = Tools.getStringParameter(request, "password");
		String birthday = Tools.getStringParameter(request, "birthday");
		String roleIds[] = request.getParameterValues("roleId");
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
		UserDto user = new UserDto();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setBirthday(birthday == "" ? null : DateHelper.stringConverDate(birthday));
		user.setAddress(address.toString());
		user.setUpdateTime(new Date());
		Set<RoleDto> roles = new HashSet<RoleDto>();
		for (String roleId : roleIds) {
			RoleDto role = new RoleDto();
			role.setId(roleId);
			roles.add(role);
		}
		user.setRoles(roles);
		JsonResult json = new JsonResult();
		try {
			userService.updateUser(user);
			json.setSuccess(true);
			json.setMessage(MessageTips.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTips.SERVICE_ERRER);
		}
		return json;
	}
}
