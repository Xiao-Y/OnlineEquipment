package com.xiaoy.base.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 用户实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:21:30
 */
@Entity
@Table(name = "USER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6181384115936269946L;

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
	Set<Role> roles = new HashSet<>();

	/******************** 临时变量***start *****************************/
	// 角色名称
	private String roleName;
	// 区域的代码
	private String area;
	// 省代码
	private String province;
	// 市代码
	private String city;
	private String[] roleId;

	/******************** 临时变量***end *****************************/

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

	@Transient
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Transient
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Transient
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Transient
	public String[] getRoleId() {
		return roleId;
	}

	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}
}
