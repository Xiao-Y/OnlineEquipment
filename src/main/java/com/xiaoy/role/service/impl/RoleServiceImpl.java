package com.xiaoy.role.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Role;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.role.dao.RoleDao;
import com.xiaoy.role.service.RoleService;
import com.xiaoy.user.service.UserService;

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

	private RoleDao roleDao;

	@Override
	public void setCommonDao(CommonDao<Role> commonDao) {
		this.roleDao = (RoleDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public Set<String> findRoleName(String id) {
		User user = userService.findObjectById(id);
		Set<Role> set = user.getRoles();
		Set<String> roleNames = new HashSet<String>();
		for (Role r : set) {
			roleNames.add(r.getRoleName());
		}
		return roleNames;
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
}
