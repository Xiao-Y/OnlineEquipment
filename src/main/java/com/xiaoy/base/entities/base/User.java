package com.xiaoy.base.entities.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xiaoy.base.entities.RoleDto;
import com.xiaoy.base.entities.base.base.BaseEntity;

/**
 * 用户实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:21:30
 */
@MappedSuperclass
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5242926587707055553L;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 出生年月
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;
	// 头像URL
	private String imageUrl;
	// 地址
	private String address;
	// 用户持有角色集合
	@JsonManagedReference
	Set<RoleDto> roles = new HashSet<>();

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false))
	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", birthday=" + birthday + ", imageUrl="
				+ imageUrl + ", address=" + address + ", roles=" + roles + ", toString()=" + super.toString() + "]";
	}
}
