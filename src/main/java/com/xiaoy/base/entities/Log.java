package com.xiaoy.base.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 日志记录
 * 
 * @author XiaoY
 * @date: 2015年8月16日 下午7:20:09
 */
@Entity
@Table(name = "LOG")
public class Log {
	private String id;
	// 用户id
	private String userId;
	// 创建日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	//运行的类
	private String runClass;
	// 日志内容
	private String content;
	// 用户所做的操作(主要是"添加"、"修改"、"删除")
	private String operation;

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_ID", nullable = false, length = 100)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "CREATE_TIME", nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CONTENT", length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "OPERATION", length = 150)
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
}
