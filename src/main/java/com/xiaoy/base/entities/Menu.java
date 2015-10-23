package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 菜单实体类对应表为Menu
 * 
 * @author XiaoY
 * @date: 2015年8月9日 下午10:20:20
 */
@Entity
@Table(name = "MENU")
public class Menu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4280720426670786694L;

	// 菜单名
	private String menuName;
	// 菜单URL
	private String menuUrl;
	// 父级菜单ID。自身为顶级则为-1
	private String parentId;
	// 父级菜单名
	private String parentName;
	// 备注
	private String remark;
	// 菜单类型。1表示叶子节点，0表示树枝节点
	private String menuType;
	// 菜单类型名称
	private String menuTypeName;
	// 排序号（显示位置）
	private int seq;

	public Menu() {
		super();
	}

	@Column(name = "MENU_NAME", length = 50)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_URL", length = 230)
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "PARENT_ID", length = 100)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "REMARK", length = 300)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "MENU_TYPE", length = 2)
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "SEQ", length = 50)
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Transient
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Transient
	public String getMenuTypeName() {
		return menuTypeName;
	}

	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
}
