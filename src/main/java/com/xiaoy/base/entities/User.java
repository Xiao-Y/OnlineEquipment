package com.xiaoy.base.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:21:30
 */
@Entity
@Table(name = "USER")
public class User {
	private String id;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 出生年月
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	// 头像URL
	private String imageUrl;
	// 地址
	private String address;
	// 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	// 更新时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	// 用户持有角色集合
	Set<Role> roles = new HashSet<>();

	// 角色名称
	private String roleName;

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USERNAME", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "IMAGE_URL", length = 150)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "ADDRESS", length = 300)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
