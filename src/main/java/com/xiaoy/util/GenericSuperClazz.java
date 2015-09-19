package com.xiaoy.util;

import java.lang.reflect.ParameterizedType;

/**
 * @author XiaoY
 * 
 * @explain 类型转换器
 * @date: 2014年11月5日 下午9:45:44
 */
public class GenericSuperClazz {
	/**
	 * 泛型类型转换器
	 * 
	 * @param clazz
	 *            要转换的泛型
	 * @return 实体对象
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClass(Class clazz) {
		// 得到泛型化的超类：CommonImpl<T>
		ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
		// 得到泛型
		Class entity = (Class) pt.getActualTypeArguments()[0];
		System.out.println("实际类型-------->" + entity);
		return entity;
	}
}
