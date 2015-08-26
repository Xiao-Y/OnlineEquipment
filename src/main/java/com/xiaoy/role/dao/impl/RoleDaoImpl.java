package com.xiaoy.role.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Role;
import com.xiaoy.role.dao.RoleDao;

@Repository
public class RoleDaoImpl extends CommonDaoImpl<Role> implements RoleDao {

	@Override
	public List<Role> getRoleListByRoleIds(List<String> roleIds) {
		String hqlWhere = " and id in(:id)";
		Map<String, Object> map = new HashMap<>();
		map.put("id", roleIds);
		return super.findCollectionByCondition(hqlWhere, map);
	}

	@Override
	public List<Role> getRoleList(Role role, String start, String limit) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = null;
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
	}
}
