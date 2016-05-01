package com.xiaoy.base.entities.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaoy.base.entities.PermissionDto;
import com.xiaoy.base.entities.UserDto;
import com.xiaoy.base.entities.base.base.BaseEntity;

/**
 * 角色实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:33:16
 */
@MappedSuperclass
public class Role extends BaseEntity implements Serializable {

	/**
	 * 
	 * @author XiaoY
	 * @date: 2016年5月1日 上午11:36:03
	 */
	private static final long serialVersionUID = 5946503657637297494L;
	// 角色名称
	private String roleName;
	// 角色Code
	private String roleCode;
	// 说明
	private String remark;
	// 授权状态，0未授权，1已授权
	private String authorizeStatus;
	// 角色持有用户的集合
	@JsonBackReference
	private Set<UserDto> users = new HashSet<>();
	// 角色持有权限的集合
	private Set<PermissionDto> permissions = new HashSet<>();

	/**
	 * 角色名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:58:54
	 */
	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名称
	 * 
	 * @param roleName
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:58:51
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 角色Code
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:59:06
	 */
	@Column(name = "ROLE_CODE", length = 50)
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * 角色Code
	 * 
	 * @param roleCode
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:59:09
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * 说明
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:59:19
	 */
	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}

	/**
	 * 说明
	 * 
	 * @param remark
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:59:23
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 角色持有用户的集合
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:00:56
	 */
	// @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	public Set<UserDto> getUsers() {
		return users;
	}

	/**
	 * 角色持有用户的集合
	 * 
	 * @param users
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:01:06
	 */
	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}

	/**
	 * 授权状态，0未授权，1已授权
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:59:48
	 */
	@Column(name = "AUTHORIZE_STATUS", length = 5)
	public String getAuthorizeStatus() {
		return authorizeStatus;
	}

	/**
	 * 授权状态，0未授权，1已授权
	 * 
	 * @param authorizeStatus
	 * @author XiaoY
	 * @date: 2016年4月24日 上午8:59:53
	 */
	public void setAuthorizeStatus(String authorizeStatus) {
		this.authorizeStatus = authorizeStatus;
	}

	/**
	 * 角色持有权限的集合
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:01:28
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PERMISSION_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "PRO_ID"))
	public Set<PermissionDto> getPermissions() {
		return permissions;
	}

	/**
	 * 角色持有权限的集合
	 * 
	 * @param permissions
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:01:34
	 */
	public void setPermissions(Set<PermissionDto> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleCode=" + roleCode + ", remark=" + remark + ", authorizeStatus="
				+ authorizeStatus + ", users=" + users + ", permissions=" + permissions + ", toString()="
				+ super.toString() + "]";
	}
}
