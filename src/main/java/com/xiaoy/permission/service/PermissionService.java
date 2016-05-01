package com.xiaoy.permission.service;

import java.util.Set;

import com.xiaoy.base.entities.PermissionDto;
import com.xiaoy.base.service.CommonService;

/**
 * 权限类
 * 
 * @author XiaoY
 * @date 2015年8月20日下午5:51:58
 */
public interface PermissionService extends CommonService<PermissionDto> {

	/**
	 * 通过用户id获得权限集合
	 * 
	 * @param id
	 * @return
	 */
	Set<String> getPermissionNames(String id);
}
