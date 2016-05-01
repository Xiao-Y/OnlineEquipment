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
	// 说明
	private String remark;
	// 授权状态，0未授权，1已授权
	private String authorizeStatus;
	// 角色持有用户的集合
	@JsonBackReference
	private Set<User> users = new HashSet<>();
	// 角色持有权限的集合
	private Set<Permission> permissions = new HashSet<>();
	/************* 临时的*****start *************/
	// 授权状态名称
	private String authorizeStatusName;

	/************* 临时的*****end *************/

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
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * 角色持有用户的集合
	 * 
	 * @param users
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:01:06
	 */
	public void setUsers(Set<User> users) {
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
	 * 授权状态名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:00:34
	 */
	@Transient
	public String getAuthorizeStatusName() {
		return authorizeStatusName;
	}

	/**
	 * 授权状态名称
	 * 
	 * @param authorizeStatusName
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:00:40
	 */
	public void setAuthorizeStatusName(String authorizeStatusName) {
		this.authorizeStatusName = authorizeStatusName;
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
	public Set<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * 角色持有权限的集合
	 * 
	 * @param permissions
	 * @author XiaoY
	 * @date: 2016年4月24日 上午9:01:34
	 */
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleCode=" + roleCode + ", remark=" + remark + ", authorizeStatus="
				+ authorizeStatus + ", authorizeStatusName=" + authorizeStatusName + ", users=" + users
				+ ", permissions=" + permissions + "]";
	}
}
