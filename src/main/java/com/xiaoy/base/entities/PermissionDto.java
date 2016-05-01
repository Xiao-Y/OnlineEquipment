package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoy.base.entities.base.Permission;

/**
 * 权限实体类
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:57:21
 */
@Entity
@Table(name = "PERMISSION")
public class PermissionDto extends Permission implements Serializable {

	private static final long serialVersionUID = -8823224219943990096L;

	@Override
	public String toString() {
		return "PermissionDto [toString()=" + super.toString() + "]";
	}

}
