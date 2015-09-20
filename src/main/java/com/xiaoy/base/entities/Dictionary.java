package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典
 * 
 * @author XiaoY
 * @date: 2015年9月20日 下午1:23:15
 */
@Entity
@Table(name = "DICTIONARY")
public class Dictionary extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4272166233799401498L;

	// 模块的名称
	private String modelName;

	// 字段名称
	private String fieldName;

	// 存储的值
	private String valueField;

	// 显示的值
	private String displayField;

	// 备注
	private String notice;

	@Column(name = "MODEL_NAME", nullable = false, length = 100)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "FIELD_NAME", nullable = false, length = 100)
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "VALUE_FIELD", nullable = false, length = 100)
	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	@Column(name = "DISPLAY_FIELD", length = 100)
	public String getDisplayField() {
		return displayField;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	@Column(name = "NOTICE", length = 200)
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}
