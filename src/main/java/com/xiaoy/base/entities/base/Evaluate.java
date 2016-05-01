package com.xiaoy.base.entities.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.xiaoy.base.entities.base.base.BaseEntity;

@MappedSuperclass
public class Evaluate extends BaseEntity implements Serializable {

	/**
	 * 
	 * @author XiaoY
	 * @date: 2016年5月1日 上午11:26:10
	 */
	private static final long serialVersionUID = -75212224244432564L;
	/* 评价信息uuid */
	private String evaluateUuid;
	/* 评价状态 */
	private String evaluateStatCode;
	/* 评价时间 */
	private Date evaluateTime;
	/* 满意度 */
	private String rankCode;
	/* 申报用户的uuid */
	private String reportingUserUuid;
	/* 申报故障信息uuid */
	private String reportingUuid;
	/* 用于标识是否真删除 */
	private String deleteFlag;

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getEvaluateUuid() {
		return evaluateUuid;
	}

	public void setEvaluateUuid(String evaluateUuid) {
		this.evaluateUuid = evaluateUuid;
	}

	public String getEvaluateStatCode() {
		return evaluateStatCode;
	}

	public void setEvaluateStatCode(String evaluateStatCode) {
		this.evaluateStatCode = evaluateStatCode;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getRankCode() {
		return rankCode;
	}

	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}

	public String getReportingUserUuid() {
		return reportingUserUuid;
	}

	public void setReportingUserUuid(String reportingUserUuid) {
		this.reportingUserUuid = reportingUserUuid;
	}

	public String getReportingUuid() {
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid) {
		this.reportingUuid = reportingUuid;
	}

	@Override
	public String toString() {
		return "Evaluate [evaluateUuid=" + evaluateUuid + ", evaluateStatCode=" + evaluateStatCode + ", evaluateTime="
				+ evaluateTime + ", rankCode=" + rankCode + ", reportingUserUuid=" + reportingUserUuid
				+ ", reportingUuid=" + reportingUuid + ", deleteFlag=" + deleteFlag + ", toString()="
				+ super.toString() + "]";
	}
}
