package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xiaoy.base.entities.base.Menu;

/**
 * 菜单实体类对应表为Menu
 * 
 * @author XiaoY
 * @date: 2015年8月9日 下午10:20:20
 */
@Entity
@Table(name = "MENU")
public class MenuDto extends Menu implements Serializable {

	private static final long serialVersionUID = 1290332007869407801L;
	// 父级菜单名
	private String parentName;
	// 菜单类型名称
	private String menuTypeName;

	public MenuDto() {
		super();
	}

	/**
	 * 父级菜单名
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:43:39
	 */
	@Transient
	public String getParentName() {
		return parentName;
	}

	/**
	 * 父级菜单名
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:43:39
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 菜单类型名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:43:58
	 */
	@Transient
	public String getMenuTypeName() {
		return menuTypeName;
	}

	/**
	 * 菜单类型名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:43:58
	 */
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}

	@Override
	public String toString() {
		return "MenuDto [parentName=" + parentName + ", menuTypeName=" + menuTypeName + ", toString()="
				+ super.toString() + "]";
	}

}
