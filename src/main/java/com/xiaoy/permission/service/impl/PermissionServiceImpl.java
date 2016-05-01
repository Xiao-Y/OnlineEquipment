package com.xiaoy.permission.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.systemmsg.role.service.RoleService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.PermissionDto;
import com.xiaoy.base.entities.RoleDto;
import com.xiaoy.base.entities.base.Permission;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.permission.dao.PermissionDao;
import com.xiaoy.permission.service.PermissionService;

@Service
public class PermissionServiceImpl extends CommonServiceImpl<PermissionDto> implements PermissionService {

	@Resource
	private RoleService roleService;

	@SuppressWarnings("unused")
	private PermissionDao permissonDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<PermissionDto> commonDao) {
		this.permissonDao = (PermissionDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public Set<String> getPermissionNames(String id) {
		List<String> roleIds = roleService.getRoleIdByUserId(id);
		List<RoleDto> roles = roleService.getRoleListByRoleIds(roleIds);
		Set<String> set = new HashSet<String>();
		for (RoleDto r : roles) {
			Set<PermissionDto> ps = r.getPermissions();
			for (Permission p : ps) {
				set.add(p.getUrl());
			}
		}
		return set;
	}
}
