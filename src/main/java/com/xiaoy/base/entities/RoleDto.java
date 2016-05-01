package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xiaoy.base.entities.base.Role;

/**
 * 角色实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:33:16
 */
@Entity
@Table(name = "ROLE")
public class RoleDto extends Role implements Serializable {

	/**
	 * 
	 * @author XiaoY
	 * @date: 2016年5月1日 上午11:42:20
	 */
	private static final long serialVersionUID = -6396727761433610759L;
	// 授权状态名称
	private String authorizeStatusName;

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

	@Override
	public String toString() {
		return "RoleDto [authorizeStatusName=" + authorizeStatusName + ", toString()=" + super.toString() + "]";
	}
}
