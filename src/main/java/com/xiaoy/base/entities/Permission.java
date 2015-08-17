package com.xiaoy.base.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 权限实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:57:21
 */
@Entity
@Table(name = "PERMISSION")
public class Permission
{
	private String id;
	private String permissionName;
	private String url;
	private Set<Role> roles = new HashSet<>();

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(name = "PERMISSION_NAME", length = 150)
	public String getPermissionName()
	{
		return permissionName;
	}

	public void setPermissionName(String permissionName)
	{
		this.permissionName = permissionName;
	}

	@Column(name = "URL", length = 300)
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
}
