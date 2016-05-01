package com.xiaoy.base.entities;

import java.io.Serializable;

import com.xiaoy.base.entities.base.Reporting;

/**
 * 申报故障信息实体类
 * 
 * @author XiaoY
 * @explain
 * 
 * @date: 2015年4月11日 下午11:16:39
 */
public class ReportingDto extends Reporting implements Serializable {
	private static final long serialVersionUID = 3647171435043472560L;

	@Override
	public String toString() {
		return "ReportingDto [toString()=" + super.toString() + "]";
	}

}
