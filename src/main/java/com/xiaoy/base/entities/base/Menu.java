package com.xiaoy.base.entities.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.xiaoy.base.entities.base.base.BaseEntity;

/**
 * 菜单实体类对应表为Menu
 * 
 * @author XiaoY
 * @date: 2015年8月9日 下午10:20:20
 */
@MappedSuperclass
public class Menu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2888396608933971172L;
	// 菜单名
	private String menuName;
	// 菜单URL
	private String menuUrl;
	// 父级菜单ID。自身为顶级则为-1
	private String parentId;
	// 备注
	private String remark;
	// 菜单类型。1表示叶子节点，0表示树枝节点,2表示按钮
	private String menuType;
	// 排序号（显示位置）
	private int seq;
	// 菜单code
	private String menuCode;

	public Menu() {
		super();
	}

	/**
	 * 菜单名
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:41:20
	 */
	@Column(name = "MENU_NAME", length = 50)
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 菜单名
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:41:20
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 菜单URL
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:41:33
	 */
	@Column(name = "MENU_URL", length = 230)
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * 菜单URL
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:41:33
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * 父级菜单ID。自身为顶级则为-1
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:41:50
	 */
	@Column(name = "PARENT_ID", length = 100)
	public String getParentId() {
		return parentId;
	}

	/**
	 * 父级菜单ID。自身为顶级则为-1
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:41:50
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 备注
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:42:07
	 */
	@Column(name = "REMARK", length = 300)
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:42:07
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 菜单类型。1表示叶子节点，0表示树枝节点,2表示按钮
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:42:39
	 */
	@Column(name = "MENU_TYPE", length = 2)
	public String getMenuType() {
		return menuType;
	}

	/**
	 * 菜单类型。1表示叶子节点，0表示树枝节点,2表示按钮
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:42:39
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/**
	 * 排序号（显示位置）
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:42:56
	 */
	@Column(name = "SEQ", length = 50)
	public int getSeq() {
		return seq;
	}

	/**
	 * 排序号（显示位置）
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:42:56
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * 菜单code
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:43:16
	 */
	@Column(name = "MENU_CODE", length = 50)
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * 菜单code
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2016年4月24日 下午12:43:16
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Override
	public String toString() {
		return "Menu [menuName=" + menuName + ", menuUrl=" + menuUrl + ", parentId=" + parentId + ", remark=" + remark
				+ ", menuType=" + menuType + ", seq=" + seq + ", menuCode=" + menuCode + ", toString()="
				+ super.toString() + "]";
	}
}
