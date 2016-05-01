package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xiaoy.base.entities.base.Bug;

/**
 * bug实体类
 * 
 * @author XiaoY
 * @date 2015年8月12日下午4:28:33
 */
@Entity
@Table(name = "BUG")
public class BugDto extends Bug implements Serializable {

	private static final long serialVersionUID = 9100328495508187536L;

	// bug状态名称
	private String statusName;
	// bug出现的父模块名称
	private String parentName;
	// bug出现的子模块名称
	private String childrenName;
	// bug严重程度名称
	private String severityName;
	// 重现规律名称
	private String reappearName;
	// bug类型名称
	private String bugTypeName;

	public BugDto() {
		super();
	}

	@Transient
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Transient
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Transient
	public String getChildrenName() {
		return childrenName;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}

	@Transient
	public String getSeverityName() {
		return severityName;
	}

	public void setSeverityName(String severityName) {
		this.severityName = severityName;
	}

	@Transient
	public String getReappearName() {
		return reappearName;
	}

	public void setReappearName(String reappearName) {
		this.reappearName = reappearName;
	}

	@Transient
	public String getBugTypeName() {
		return bugTypeName;
	}

	public void setBugTypeName(String bugTypeName) {
		this.bugTypeName = bugTypeName;
	}

	@Override
	public String toString() {
		return "BugDto [statusName=" + statusName + ", parentName=" + parentName + ", childrenName=" + childrenName
				+ ", severityName=" + severityName + ", reappearName=" + reappearName + ", bugTypeName=" + bugTypeName
				+ "]" + super.toString();
	}
}
