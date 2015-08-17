package com.xiaoy.base.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:33:16
 */
@Entity
@Table(name = "ROLE")
public class Role
{
	private String id;
	// 角色名称
	private String roleName;
	// 角色持有用户的集合
	private Set<User> users = new HashSet<>();
	// 角色持有权限的集合
	private Set<Permission> permissions = new HashSet<>();

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

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	public Set<User> getUsers()
	{
		return users;
	}

	public void setUsers(Set<User> users)
	{
		this.users = users;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "per_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "pro_id"))
	public Set<Permission> getPermissions()
	{
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions)
	{
		this.permissions = permissions;
	}
}
