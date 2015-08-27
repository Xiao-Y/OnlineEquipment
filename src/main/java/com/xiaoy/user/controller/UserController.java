package com.xiaoy.user.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Role;
import com.xiaoy.base.entities.User;
import com.xiaoy.user.service.UserService;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.Tools;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}

	@RequestMapping("/getUserList")
	public @ResponseBody JsonResult getUserList(HttpServletRequest request) {
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
}
