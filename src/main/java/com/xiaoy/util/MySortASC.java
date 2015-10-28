package com.xiaoy.util;

import java.util.Comparator;

/**
 * 自定义对发送的下拉列表进行升序排序
 * 
 * @author XiaoY
 * @date: 2015年10月28日 下午7:51:58
 */
public class MySortASC implements Comparator<CheckBox> {

	@Override
	public int compare(CheckBox o1, CheckBox o2) {
		return o1.getValueField().compareTo(o2.getValueField());
	}
}
