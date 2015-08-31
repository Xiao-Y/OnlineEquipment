package com.xiaoy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

public class ReadPropertyXML {

	/**
	 * 用于从内存中读取所有的property.xml中的属性配置<br>
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	// public static PropertyModel getReadPropertyXML(HttpServletRequest
	// request, String modelStr, String fieldStr, String keyStr) {
	public static List<PropertyModel> getReadPropertyXML(Element rootElement) {
		// ServletContext servletContext = request.getServletContext();
		// Element element = (Element) servletContext.getAttribute("PROPERTY");
		List<PropertyModel> list = new ArrayList<>();
		Element element = rootElement;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			PropertyModel pm = new PropertyModel();
			Element model = models.next();
			String modelName = model.attributeValue("name");
			pm.setModel(modelName);
			// 读取指定模块的数据
			Iterator<Element> datas = model.elementIterator();
			while (datas.hasNext()) {
				Element data = datas.next();
				String field = data.attributeValue("field");
				pm.setField(field);
				Iterator<Element> maps = data.elementIterator();
				Map<String, String> mapData = new HashMap<String, String>();
				while (maps.hasNext()) {
					Element map = maps.next();
					String key = map.attributeValue("key");
					String value = map.attributeValue("value");
					mapData.put(key, value);
				}
				pm.setData(mapData);
			}
			list.add(pm);
		}
		return list;
	}

	/**
	 * 用于从内存中读取指定模块名称的property.xml中的属性配置<br>
	 * 
	 * @param rootElement
	 * @param modelStr
	 *            模块的名称
	 * 
	 * @return
	 *
	 * @date 2015年8月31日上午9:02:15
	 */
	@SuppressWarnings("unchecked")
	// public static PropertyModel getReadPropertyXML(HttpServletRequest
	// request, String modelStr, String fieldStr, String keyStr) {
	public static List<PropertyModel> getReadPropertyXML(Element rootElement, String modelStr) {
		// ServletContext servletContext = request.getServletContext();
		// Element element = (Element) servletContext.getAttribute("PROPERTY");
		List<PropertyModel> list = new ArrayList<>();
		Element element = rootElement;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			Element model = models.next();
			String modelName = model.attributeValue("name");
			if (!StringUtils.isEmpty(modelStr) && modelName.equals(modelStr)) {
				PropertyModel pm = new PropertyModel();
				pm.setModel(modelName);
				// 读取指定模块的数据
				Iterator<Element> datas = model.elementIterator();
				while (datas.hasNext()) {
					Element data = datas.next();
					String field = data.attributeValue("field");
					pm.setField(field);
					Iterator<Element> maps = data.elementIterator();
					Map<String, String> mapData = new HashMap<String, String>();
					while (maps.hasNext()) {
						Element map = maps.next();
						String key = map.attributeValue("key");
						String value = map.attributeValue("value");
						mapData.put(key, value);
					}
					pm.setData(mapData);
				}
				list.add(pm);
				break;
			}
		}
		return list;
	}

	/**
	 * 用于从内存中读取指定模块名称和字段的property.xml中的属性配置<br>
	 * 
	 * @param rootElement
	 * @param modelStr
	 *            模块的名称
	 * @param fieldStr
	 *            字段名
	 * @return
	 *
	 * @date 2015年8月31日上午9:01:56
	 */
	@SuppressWarnings("unchecked")
	// public static PropertyModel getReadPropertyXML(HttpServletRequest
	// request, String modelStr, String fieldStr, String keyStr) {
	public static List<PropertyModel> getReadPropertyXML(Element rootElement, String modelStr, String fieldStr) {
		// ServletContext servletContext = request.getServletContext();
		// Element element = (Element) servletContext.getAttribute("PROPERTY");
		List<PropertyModel> list = new ArrayList<>();
		Element element = rootElement;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			Element model = models.next();
			String modelName = model.attributeValue("name");
			if (!StringUtils.isEmpty(modelStr) && modelName.equals(modelStr)) {
				PropertyModel pm = new PropertyModel();
				pm.setModel(modelName);
				// 读取指定模块的数据
				Iterator<Element> datas = model.elementIterator();
				while (datas.hasNext()) {
					Element data = datas.next();
					String field = data.attributeValue("field");
					if (!StringUtils.isEmpty(fieldStr) && field.equals(fieldStr)) {
						pm.setField(field);
						Iterator<Element> maps = data.elementIterator();
						Map<String, String> mapData = new HashMap<String, String>();
						while (maps.hasNext()) {
							Element map = maps.next();
							String key = map.attributeValue("key");
							String value = map.attributeValue("value");
							mapData.put(key, value);
						}
						pm.setData(mapData);
						break;
					}
				}
				list.add(pm);
				break;
			}
		}
		return list;
	}

	/**
	 * * 用于从内存中读取指定模块名称、字段和关键字的property.xml中的属性配置<br>
	 * 
	 * @param rootElement
	 * @param modelStr
	 *            模块的名称
	 * @param fieldStr
	 *            字段名
	 * @param keyStr
	 *            关键字
	 * @return
	 *
	 * @date 2015年8月31日上午9:02:44
	 */
	@SuppressWarnings("unchecked")
	// public static PropertyModel getReadPropertyXML(HttpServletRequest
	// request, String modelStr, String fieldStr, String keyStr) {
	public static List<PropertyModel> getReadPropertyXML(Element rootElement, String modelStr, String fieldStr, String keyStr) {
		// ServletContext servletContext = request.getServletContext();
		// Element element = (Element) servletContext.getAttribute("PROPERTY");
		List<PropertyModel> list = new ArrayList<>();
		Element element = rootElement;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			Element model = models.next();
			String modelName = model.attributeValue("name");
			if (!StringUtils.isEmpty(modelStr) && modelName.equals(modelStr)) {
				PropertyModel pm = new PropertyModel();
				pm.setModel(modelName);
				// 读取指定模块的数据
				Iterator<Element> datas = model.elementIterator();
				while (datas.hasNext()) {
					Element data = datas.next();
					String field = data.attributeValue("field");
					if (!StringUtils.isEmpty(fieldStr) && field.equals(fieldStr)) {
						pm.setField(field);
						Iterator<Element> maps = data.elementIterator();
						Map<String, String> mapData = new HashMap<String, String>();
						while (maps.hasNext()) {
							Element map = maps.next();
							String key = map.attributeValue("key");
							String value = map.attributeValue("value");
							if (!StringUtils.isEmpty(keyStr) && key.equals(keyStr)) {
								mapData.put(key, value);
								break;
							}
						}
						pm.setData(mapData);
						break;
					}
				}
				list.add(pm);
				break;
			}
		}
		return list;
	}
}
