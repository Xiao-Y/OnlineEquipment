package com.xiaoy.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * 用于存放属性配置文件模型
 * 
 * @author XiaoY
 * @date: 2015年8月29日 下午11:01:23
 */
@Deprecated
public class PropertyModel {

	private String model;
	private Map<String, Map<String, String>> datas = new TreeMap<>();

	// private Map<String, String> data = new TreeMap<>();

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Map<String, Map<String, String>> getDatas() {
		return datas;
	}

	public void setDatas(Map<String, Map<String, String>> datas) {
		this.datas = datas;
	}

	// public Map<String, String> getData() {
	// return data;
	// }
	//
	// public void setData(Map<String, String> data) {
	// this.data = data;
	// }

	@Override
	public String toString() {
		return "PropertyModel [model=" + model + ", datas=" + datas + "]";
	}
}
