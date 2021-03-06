package com.xiaoy.base.entities.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.xiaoy.base.entities.base.base.BaseEntity;

/**
 * 审核申报信息表
 * 
 * @author XiaoY
 * @date 2015年4月14日
 */
@MappedSuperclass
public class Audit extends BaseEntity implements Serializable {

	/**
	 * 
	 * @author XiaoY
	 * @date: 2016年5月1日 上午11:24:54
	 */
	private static final long serialVersionUID = 8385707097844850853L;
	/* 审核状态 */
	private String auditStatCode;
	/* 审核时间 */
	private Date auditTime;
	/* 维护状态 */
	private String maintainStatCode;
	/* 完成时间 */
	private Date finishTime;
	/* 驳回原因 */
	private String failAccount;
	/* 申报故障信息uuid */
	private String reportingUuid;
	/* 维护人员Uuid */
	private String maintainUuid;
	/* 审核uuid */
	private String auditUserUuid;

	public String getAuditUserUuid() {
		return auditUserUuid;
	}

	public void setAuditUserUuid(String auditUserUuid) {
		this.auditUserUuid = auditUserUuid;
	}

	public String getMaintainUuid() {
		return maintainUuid;
	}

	public void setMaintainUuid(String maintainUuid) {
		this.maintainUuid = maintainUuid;
	}

	public String getAuditStatCode() {
		return auditStatCode;
	}

	public void setAuditStatCode(String auditStatCode) {
		this.auditStatCode = auditStatCode;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getMaintainStatCode() {
		return maintainStatCode;
	}

	public void setMaintainStatCode(String maintainStatCode) {
		this.maintainStatCode = maintainStatCode;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getFailAccount() {
		return failAccount;
	}

	public void setFailAccount(String failAccount) {
		this.failAccount = failAccount;
	}

	public String getReportingUuid() {
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid) {
		this.reportingUuid = reportingUuid;
	}

	@Override
	public String toString() {
		return "Audit [auditStatCode=" + auditStatCode + ", auditTime=" + auditTime + ", maintainStatCode="
				+ maintainStatCode + ", finishTime=" + finishTime + ", failAccount=" + failAccount + ", reportingUuid="
				+ reportingUuid + ", maintainUuid=" + maintainUuid + ", auditUserUuid=" + auditUserUuid + "]";
	}

}
