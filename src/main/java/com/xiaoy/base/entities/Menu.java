package com.xiaoy.base.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 菜单实体类对应表为Menu
 * 
 * @author XiaoY
 * @date: 2015年8月9日 下午10:20:20
 */
@Entity
@Table(name = "MENU")
public class Menu
{
	private String id;
	private String menuName;
	private String menuUrl;
	private String parentId;
	private String parentName;
	private String remark;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	private String menuType;
	private int seq;

	public Menu(String id, String menuName, String menuUrl, String parentId, String remark, String menuType, int seq)
	{
		super();
		this.id = id;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.parentId = parentId;
		this.remark = remark;
		this.menuType = menuType;
		this.seq = seq;
	}

	public Menu()
	{
		super();
	}

	public Menu(String id, String menuName, String menuUrl, String parentId, String remark, Date createTime, Date updateTime, String menuType, int seq)
	{
		super();
		this.id = id;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.parentId = parentId;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.menuType = menuType;
		this.seq = seq;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(name = "MENU_NAME", length = 50)
	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	@Column(name = "MENU_URL", length = 230)
	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	@Column(name = "PARENT_ID", length = 100)
	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	@Column(name = "REMARK", length = 300)
	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	@Column(name = "MENU_TYPE", length = 2)
	public String getMenuType()
	{
		return menuType;
	}

	public void setMenuType(String menuType)
	{
		this.menuType = menuType;
	}

	@Column(name = "SEQ", length = 50)
	public int getSeq()
	{
		return seq;
	}

	public void setSeq(int seq)
	{
		this.seq = seq;
	}

	@Transient
	public String getParentName()
	{
		return parentName;
	}

	public void setParentName(String parentName)
	{
		this.parentName = parentName;
	}
}
