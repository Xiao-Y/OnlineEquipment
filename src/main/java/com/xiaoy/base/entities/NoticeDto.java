package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoy.base.entities.base.Notice;

/**
 * 公告信息的实体类<br/>
 * 用于存放管理员发布的公告<br/>
 * 
 * @author XiaoY
 * @date: 2015年10月3日 下午8:29:50
 */
@Entity
@Table(name = "NOTICE")
public class NoticeDto extends Notice implements Serializable {

	private static final long serialVersionUID = 5662378202476970943L;

	@Override
	public String toString() {
		return "NoticeDto [toString()=" + super.toString() + "]";
	}

}
