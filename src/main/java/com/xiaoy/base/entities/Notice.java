package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 公告信息的实体类<br/>
 * 用于存放管理员发布的公告<br/>
 * 
 * @author XiaoY
 * @date: 2015年10月3日 下午8:29:50
 */
@Entity
@Table(name = "NOTICE")
public class Notice extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6893743710713977635L;

	/* 公告标题 */
	private String noticeTit;

	/* 公告信息 */
	private String notice;

	/* 公告人 */
	private String noticeName;

	@Column(name = "NOTICE_TIT", length = 50, nullable = false)
	public String getNoticeTit() {
		return noticeTit;
	}

	public void setNoticeTit(String noticeTit) {
		this.noticeTit = noticeTit;
	}

	@Column(name = "NOTICE", length = 350, nullable = false)
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Column(name = "NOTICE_NAME", length = 50, nullable = false)
	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	@Override
	public String toString() {
		return "Notice [noticeTit=" + noticeTit + ", notice=" + notice + ", noticeName=" + noticeName + "]" + super.toString();
	}

}
