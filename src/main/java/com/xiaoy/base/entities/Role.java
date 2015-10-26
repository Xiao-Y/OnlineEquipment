package com.xiaoy.base.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 角色实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:33:16
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5600137110830037129L;
	// 角色名称
	private String roleName;
	// 角色Code
	private String roleCode;
	// 授权状态，0未授权，1已授权
	private String authorizeStatus;
	// 授权状态名称
	private String authorizeStatusName;
	// 角色持有用户的集合
	@JsonBackReference
	private Set<User> users = new HashSet<>();
	// 角色持有权限的集合
	private Set<Permission> permissions = new HashSet<>();

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_CODE", length = 50)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	// @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Column(name = "AUTHORIZE_STATUS", length = 5)
	public String getAuthorizeStatus() {
		return authorizeStatus;
	}

	public void setAuthorizeStatus(String authorizeStatus) {
		this.authorizeStatus = authorizeStatus;
	}

	@Transient
	public String getAuthorizeStatusName() {
		return authorizeStatusName;
	}

	public void setAuthorizeStatusName(String authorizeStatusName) {
		this.authorizeStatusName = authorizeStatusName;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PERMISSION_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "PRO_ID"))
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleCode=" + roleCode + ", authorizeStatus=" + authorizeStatus + ", authorizeStatusName="
				+ authorizeStatusName + ", users=" + users + ", permissions=" + permissions + "]";
	}
}
