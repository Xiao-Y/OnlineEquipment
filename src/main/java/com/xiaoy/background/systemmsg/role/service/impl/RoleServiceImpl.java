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

import com.xiaoy.background.resourcesmsg.dictionary.controller.DictionaryType;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.background.systemmsg.role.dao.RoleDao;
import com.xiaoy.background.systemmsg.role.service.RoleService;
import com.xiaoy.background.usermsg.user.service.UserService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.DictionaryDto;
import com.xiaoy.base.entities.MenuDto;
import com.xiaoy.base.entities.RoleDto;
import com.xiaoy.base.entities.UserDto;
import com.xiaoy.base.entities.base.Role;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.util.Tools;

/**
 * 角色实现类
 * 
 * @author XiaoY
 * @date 2015年8月20日下午5:52:17
 */
@Service
public class RoleServiceImpl extends CommonServiceImpl<RoleDto> implements RoleService {

	// 工具服务
	@Resource
	private Tools tools;

	@Resource
	private UserService userService;

	@Resource
	private MenuService menuService;

	private RoleDao roleDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<RoleDto> commonDao) {
		this.roleDao = (RoleDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public Set<String> findRoleCode(String id) {
		UserDto user = userService.findObjectById(id);
		Set<RoleDto> set = user.getRoles();
		Set<String> roleCodes = new HashSet<String>();
		for (Role r : set) {
			roleCodes.add(r.getRoleCode());
		}
		return roleCodes;
	}

	@Override
	public List<String> getRoleIdByUserId(String id) {
		UserDto user = userService.findObjectById(id);
		Set<RoleDto> set = user.getRoles();
		List<String> roleIds = new ArrayList<String>();
		for (Role r : set) {
			roleIds.add(r.getId());
		}
		return roleIds;
	}

	@Override
	public List<RoleDto> getRoleListByRoleIds(List<String> roleIds) {
		return roleDao.getRoleListByRoleIds(roleIds);
	}

	@Override
	public List<RoleDto> getRoleList(HttpServletRequest request, RoleDto role, String start, String limit) {
		List<RoleDto> list = roleDao.getRoleList(role, start, limit);
		for (RoleDto r : list) {
			// 2015-10-01 by XiaoY 修改：废弃的方法-----start
			// PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "role", "authorizeStatus", r.getAuthorizeStatus());
			// Map<String, Map<String, String>> datas = propertyModel.getDatas();
			// Map<String, String> map = datas.get("authorizeStatus");
			// String authorizeStatusName = map.get(r.getAuthorizeStatus());
			String authorizeStatus = r.getAuthorizeStatus();
			DictionaryDto dictionary = tools.getDictionaryByModelCodeAndFieldCodeAndValueFiel(
					DictionaryType.ROLE_MODEL_CODE_ROLE, DictionaryType.ROLE_FIELD_CODE_AUTHORIZESTATUS,
					authorizeStatus);
			if (dictionary != null) {
				authorizeStatus = dictionary.getDisplayField();
			}
			// 2015-10-01 by XiaoY 修改：废弃的方法-----end
			r.setAuthorizeStatusName(authorizeStatus);
		}
		return list;
	}

	@Override
	public List<MenuDto> getParentMenuList() {
		List<MenuDto> parentMenus = menuService.getParentMenuList();
		return parentMenus;
	}

	@Override
	public List<MenuDto> getChildMenuList() {
		List<MenuDto> childMenus = menuService.getChildMenuList();
		return childMenus;
	}

	@Override
	public List<Object> buildTree() {
		// 获取父级菜单
		List<MenuDto> parentMenu = this.getParentMenuList();
		// 获取所有的子菜单
		List<MenuDto> childMenu = this.getChildMenuList();

		List<Object> json = new ArrayList<Object>();
		for (MenuDto parent : parentMenu) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", parent.getId());
			map.put("expanded", true);// 是否展开
			map.put("text", parent.getMenuName());// 菜单名称
			map.put("index", parent.getSeq());
			map.put("leaf", false);
			map.put("checked", false);
			List<Object> list = new ArrayList<Object>();
			for (MenuDto child : childMenu) {
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
	public List<RoleDto> getRoleList() {
		return roleDao.findCollectionByCondition(null, null);
	}
}
