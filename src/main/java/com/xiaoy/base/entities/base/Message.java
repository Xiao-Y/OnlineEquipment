package com.xiaoy.base.entities.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.xiaoy.base.entities.base.base.BaseEntity;

/**
 * 留言管理
 * 
 * @author XiaoY
 * @date: 2015年5月4日 下午11:21:18
 */
@MappedSuperclass
public class Message extends BaseEntity implements Serializable {
	/**
	 * 
	 * @author XiaoY
	 * @date: 2016年5月1日 上午11:27:32
	 */
	private static final long serialVersionUID = -3022761994806794569L;
	/* 留言uuid */
	private String messageUuid;
	/* 标题 */
	private String titleMes;
	/* 回复内容 */
	private String replyMes;
	/* 回复状态 */
	private String replySata;
	/* 留言时间 */
	private Date messageTime;
	/* 留言内容 */
	private String messageMes;

	/* 用户对象 */
	private User user = new User();

	public String getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}

	public String getTitleMes() {
		return titleMes;
	}

	public void setTitleMes(String titleMes) {
		this.titleMes = titleMes;
	}

	public String getReplyMes() {
		return replyMes;
	}

	public void setReplyMes(String replyMes) {
		this.replyMes = replyMes;
	}

	public String getReplySata() {
		return replySata;
	}

	public void setReplySata(String replySata) {
		this.replySata = replySata;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessageMes() {
		return messageMes;
	}

	public void setMessageMes(String messageMes) {
		this.messageMes = messageMes;
	}

	@Override
	public String toString() {
		return "Message [messageUuid=" + messageUuid + ", titleMes=" + titleMes + ", replyMes=" + replyMes
				+ ", replySata=" + replySata + ", messageTime=" + messageTime + ", messageMes=" + messageMes
				+ ", user=" + user + ", toString()=" + super.toString() + "]";
	}
}
