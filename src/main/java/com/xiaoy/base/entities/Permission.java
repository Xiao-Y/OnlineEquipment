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

	// 权限名称
	private String permissionName;
	// 菜单url
	private String url;
	// 菜单code
	private String menuCode;

	// private Set<Role> roles = new HashSet<>();

	/**
	 * 权限名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:18:25
	 */
	@Column(name = "PERMISSION_NAME", length = 150)
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * 权限名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:18:25
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * 菜单url
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:18:40
	 */
	@Column(name = "URL", length = 300)
	public String getUrl() {
		return url;
	}

	/**
	 * 菜单url
	 * 
	 * @param url
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:18:44
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 菜单code
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:18:54
	 */
	@Column(name = "MENU_CODE", length = 150)
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * 菜单code
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:18:54
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Override
	public String toString() {
		return "Permission [permissionName=" + permissionName + ", url=" + url + ", menuCode=" + menuCode + "]";
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
