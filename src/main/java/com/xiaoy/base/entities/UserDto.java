package com.xiaoy.base.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.xiaoy.base.entities.base.User;

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
public class UserDto extends User implements Serializable {

	private static final long serialVersionUID = 3019914133810899393L;
	// 角色名称
	private String roleName;
	// 区域的代码
	private String area;
	// 省代码
	private String province;
	// 市代码
	private String city;
	private String[] roleId;

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

	@Override
	public String toString() {
		return "UserDto [roleName=" + roleName + ", area=" + area + ", province=" + province + ", city=" + city
				+ ", roleId=" + Arrays.toString(roleId) + ", toString()=" + super.toString() + "]";
	}
}
