package com.xiaoy.base.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xiaoy.base.entities.base.Log;

/**
 * 日志记录
 * 
 * @author XiaoY
 * @date: 2015年8月16日 下午7:20:09
 */
@Entity
@Table(name = "LOG")
public class LogDto extends Log implements Serializable {

	private static final long serialVersionUID = 5731357397753838240L;

	// 用于存放用户的id
	private List<Object> userIds = new ArrayList<>();

	@Transient
	public List<Object> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Object> userIds) {
		this.userIds = userIds;
	}

	@Override
	public String toString() {
		return "LogDto [userIds=" + userIds + "]" + super.toString();
	}
}
