package com.xiaoy.user.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Role;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.entities.Zip;
import com.xiaoy.user.service.UserService;
import com.xiaoy.util.JsonResult;
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
			u.setRoleName(str.toString());
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
		if(!StringUtils.isEmpty(city)){
			zip.setParentId(city);
		}else if(!StringUtils.isEmpty(province)){
			zip.setParentId(province);
		}else{
			zip.setLevelType("1");
		}
		JsonResult json = new JsonResult();
		List<Zip> list = zipService.getZipCondition(zip);
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}
}
