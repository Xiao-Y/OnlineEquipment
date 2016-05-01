package com.xiaoy.base.entities.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.xiaoy.base.entities.base.base.BaseEntity;

/**
 * bug实体类
 * 
 * @author XiaoY
 * @date 2015年8月12日下午4:28:33
 */
@MappedSuperclass
public class Bug extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3573099077777635304L;

	// bug标题
	private String title;
	// 缺陷描述 (重现的步骤)
	private String note;
	// bug上传图片路径
	private String imgUrl;
	// bug状态。0未修改，1已经修改
	private String status;
	// bug修改说明
	private String reviseExplain;
	// bug出现的父模块id
	private String parentId;
	// bug出现的子模块id
	private String childrenId;
	// bug严重程度
	private String severity;
	// 重现规律
	private String reappear;
	// bug类型
	private String bugType;

	public Bug() {
		super();
	}

	@Column(name = "TITLE", nullable = false, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "NOTE", nullable = false, length = 8000)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "IMG_URL", length = 150)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "STATUS", length = 5)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "REVISE_EXPLAIN", length = 8000)
	public String getReviseExplain() {
		return reviseExplain;
	}

	public void setReviseExplain(String reviseExplain) {
		this.reviseExplain = reviseExplain;
	}

	@Column(name = "PARENT_ID", length = 100)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "CHILDREN_ID", length = 100)
	public String getChildrenId() {
		return childrenId;
	}

	public void setChildrenId(String childrenId) {
		this.childrenId = childrenId;
	}

	@Column(name = "SEVERITY", length = 5)
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Column(name = "REAPPEAR", length = 5)
	public String getReappear() {
		return reappear;
	}

	public void setReappear(String reappear) {
		this.reappear = reappear;
	}

	@Column(name = "BUG_TYPE", length = 5)
	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	@Override
	public String toString() {
		return "Bug [title=" + title + ", note=" + note + ", imgUrl=" + imgUrl + ", status=" + status
				+ ", reviseExplain=" + reviseExplain + ", parentId=" + parentId + ", childrenId=" + childrenId
				+ ", severity=" + severity + ", reappear=" + reappear + ", bugType=" + bugType + "]";
	}
}
