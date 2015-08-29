package com.xiaoy.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * 用于存放属性配置文件模型
 * 
 * @author XiaoY
 * @date: 2015年8月29日 下午11:01:23
 */
public class PropertyModel {

	private String model;
	private String field;
	private Map<String, String> data = new TreeMap<>();

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PropertyModel [model=" + model + ", field=" + field + ", data=" + data + "]";
	}
}
