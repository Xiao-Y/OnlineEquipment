package com.xiaoy.permission.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Permission;
import com.xiaoy.base.entities.Role;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.permission.dao.PermissionDao;
import com.xiaoy.permission.service.PermissionService;
import com.xiaoy.role.service.RoleService;

@Service
public class PermissionServiceImpl extends CommonServiceImpl<Permission> implements PermissionService
{

	@Resource
	private RoleService roleService;

	@SuppressWarnings("unused")
	private PermissionDao permissonDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Permission> commonDao)
	{
		this.permissonDao = (PermissionDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public Set<String> getPermissionNames(String id)
	{
		List<String> roleIds = roleService.getRoleIdByUserId(id);
		List<Role> roles = roleService.getRoleListByRoleIds(roleIds);
		Set<String> set = new HashSet<String>();
		for (Role r : roles)
		{
			Set<Permission> ps = r.getPermissions();
			for (Permission p : ps)
			{
				set.add(p.getUrl());
			}
		}
		return set;
	}
}
