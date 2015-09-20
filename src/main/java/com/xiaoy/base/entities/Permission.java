package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:57:21
 */
@Entity
@Table(name = "PERMISSION")
public class Permission extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5265798075097339678L;

	private String permissionName;
	private String url;

	// private Set<Role> roles = new HashSet<>();

	@Column(name = "PERMISSION_NAME", length = 150)
	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Column(name = "URL", length = 300)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// //@ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
	// @ManyToMany(cascade = CascadeType.ALL)
	// public Set<Role> getRoles() {
	// return roles;
	// }
	//
	// public void setRoles(Set<Role> roles) {
	// this.roles = roles;
	// }
}
