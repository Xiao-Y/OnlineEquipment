package com.xiaoy.log.service;

/**
 * 用于存储日志中出现的常量
 * 
 * @author XiaoY
 * @date 2015年8月17日上午8:48:27
 */
public enum HandleTyepEnum {

	DELERE("delete", "删除操作"), SAVE("save", "保存操作"), UPDATE("update", "更新操作");

	private String name;
	private String value;

	private HandleTyepEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
