package com.xiaoy.shiro.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Service;

import com.xiaoy.background.systemmsg.role.service.RoleService;
import com.xiaoy.background.usermsg.user.service.UserService;
import com.xiaoy.base.entities.User;
import com.xiaoy.permission.service.PermissionService;

/**
 * shiro 控制权限
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:19:02
 */
@Service
public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	@Resource
	private PermissionService permissionService;

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录的用户名
		String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
		User user = userService.findByName(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 通过用户id获得角色Code集合
			Set<String> roleCodes = roleService.findRoleCode(user.getId());
			// 通过用户id获得权限集合
			Set<String> permissions = permissionService.getPermissionNames(user.getId());
			// 登录的用户有多少个角色
			info.setRoles(roleCodes);
			info.addStringPermissions(permissions);
			return info;
		}
		return null;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = userService.findByName(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		}
		return null;
	}

	/**
	 * 清除权限信息
	 * 
	 * @param principal
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
}
