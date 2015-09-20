package com.xiaoy.base.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 日志记录
 * 
 * @author XiaoY
 * @date: 2015年8月16日 下午7:20:09
 */
@Entity
@Table(name = "LOG")
public class Log extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3048622707076335461L;

	// 用户id
	private String userId;
	// ip地址
	private String ipAddr;
	// 运行的类
	private String runClass;
	// 日志内容
	private String content;
	// 用户所做的操作(主要是"添加"、"修改"、"删除")
	private String operation;

	// 用于存放用户的id
	private List<Object> userIds = new ArrayList<>();

	@Column(name = "USER_ID", nullable = false, length = 100)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "CONTENT", length = 8000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "OPERATION", length = 5000)
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "RUN_CLASS", length = 150)
	public String getRunClass() {
		return runClass;
	}

	public void setRunClass(String runClass) {
		this.runClass = runClass;
	}

	@Transient
	public List<Object> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Object> userIds) {
		this.userIds = userIds;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Override
	public String toString() {
		return "Log [userId=" + userId + ", ipAddr=" + ipAddr + ", runClass=" + runClass + ", content=" + content + ", operation=" + operation + ", userIds="
				+ userIds + "]";
	}
}
