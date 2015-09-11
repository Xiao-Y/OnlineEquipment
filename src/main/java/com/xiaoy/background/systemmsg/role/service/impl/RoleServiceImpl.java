package com.xiaoy.background.systemmsg.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.background.systemmsg.role.dao.RoleDao;
import com.xiaoy.background.systemmsg.role.service.RoleService;
import com.xiaoy.background.usermsg.user.service.UserService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.entities.Role;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.util.PropertyModel;
import com.xiaoy.util.ReadPropertyXML;

/**
 * 角色实现类
 * 
 * @author XiaoY
 * @date 2015年8月20日下午5:52:17
 */
@Service
public class RoleServiceImpl extends CommonServiceImpl<Role> implements RoleService {

	@Resource
	private UserService userService;

	@Resource
	private MenuService menuService;

	private RoleDao roleDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Role> commonDao) {
		this.roleDao = (RoleDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public Set<String> findRoleCode(String id) {
		User user = userService.findObjectById(id);
		Set<Role> set = user.getRoles();
		Set<String> roleCodes = new HashSet<String>();
		for (Role r : set) {
			roleCodes.add(r.getRoleCode());
		}
		return roleCodes;
	}

	@Override
	public List<String> getRoleIdByUserId(String id) {
		User user = userService.findObjectById(id);
		Set<Role> set = user.getRoles();
		List<String> roleIds = new ArrayList<String>();
		for (Role r : set) {
			roleIds.add(r.getId());
		}
		return roleIds;
	}

	@Override
	public List<Role> getRoleListByRoleIds(List<String> roleIds) {
		return roleDao.getRoleListByRoleIds(roleIds);
	}

	@Override
	public List<Role> getRoleList(HttpServletRequest request, Role role, String start, String limit) {
		List<Role> list = roleDao.getRoleList(role, start, limit);
		for (Role r : list) {
			PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "role", "authorizeStatus", r.getAuthorizeStatus());
			Map<String, Map<String, String>> datas = propertyModel.getDatas();
			Map<String, String> map = datas.get("authorizeStatus");
			String authorizeStatusName = map.get(r.getAuthorizeStatus());
			r.setAuthorizeStatusName(authorizeStatusName);
		}
		return list;
	}

	@Override
	public List<Menu> getParentMenuList() {
		List<Menu> parentMenus = menuService.getParentMenuList();
		return parentMenus;
	}

	@Override
	public List<Menu> getChildMenuList() {
		List<Menu> childMenus = menuService.getChildMenuList();
		return childMenus;
	}

	@Override
	public List<Object> buildTree() {
		// 获取父级菜单
		List<Menu> parentMenu = this.getParentMenuList();
		// 获取所有的子菜单
		List<Menu> childMenu = this.getChildMenuList();

		List<Object> json = new ArrayList<Object>();
		for (Menu parent : parentMenu) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", parent.getId());
			map.put("expanded", true);// 是否展开
			map.put("text", parent.getMenuName());// 菜单名称
			map.put("index", parent.getSeq());
			map.put("leaf", false);
			map.put("checked", false);
			List<Object> list = new ArrayList<Object>();
			for (Menu child : childMenu) {
				if (parent.getId().equals(child.getParentId())) {
					Map<String, Object> childMap = new HashMap<>();
					childMap.put("id", child.getId());
					childMap.put("text", child.getMenuName());// 菜单名称
					childMap.put("index", child.getSeq());
					childMap.put("parentId", child.getParentId());
					childMap.put("url", "../" + child.getMenuUrl());
					childMap.put("leaf", true);
					childMap.put("checked", false);
					list.add(childMap);
				}
			}
			map.put("children", list);
			json.add(map);
		}
		return json;
	}

	@Override
	public List<Role> getRoleList() {
		return roleDao.findCollectionByCondition(null, null);
	}
}