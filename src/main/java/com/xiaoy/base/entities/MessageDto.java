package com.xiaoy.base.entities;

import java.io.Serializable;

import com.xiaoy.base.entities.base.Message;

/**
 * 留言管理
 * 
 * @author XiaoY
 * @date: 2015年5月4日 下午11:21:18
 */
public class MessageDto extends Message implements Serializable {

	/**
	 * 
	 * @author XiaoY
	 * @date: 2016年5月1日 上午11:31:23
	 */
	private static final long serialVersionUID = -8044946868392711932L;

	@Override
	public String toString() {
		return "Message []" + super.toString();
	}

}
