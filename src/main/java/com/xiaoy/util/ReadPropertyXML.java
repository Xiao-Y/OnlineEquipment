package com.xiaoy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

/**
 * 用于读取属性文件
 * 
 * @author XiaoY
 * @date: 2015年8月31日 下午11:07:28
 */
@Deprecated
public class ReadPropertyXML {

	/**
	 * 用于从内存中读取所有的property.xml中的属性配置<br>
	 * 
	 * @param request
	 * @return
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public static List<PropertyModel> getReadPropertyXML(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		Element element = (Element) servletContext.getAttribute("PROPERTY");
		List<PropertyModel> list = new ArrayList<>();
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			PropertyModel pm = new PropertyModel();
			Element model = models.next();
			String modelName = model.attributeValue("name");
			pm.setModel(modelName);
			// 读取指定模块的数据
			Iterator<Element> datas = model.elementIterator();
			Map<String, Map<String, String>> fields = new HashMap<>();
			while (datas.hasNext()) {
				Element data = datas.next();
				String field = data.attributeValue("field");
				Iterator<Element> maps = data.elementIterator();
				Map<String, String> mapData = new HashMap<String, String>();
				while (maps.hasNext()) {
					Element map = maps.next();
					String key = map.attributeValue("key");
					String value = map.attributeValue("value");
					mapData.put(key, value);
				}
				fields.put(field, mapData);
				pm.setDatas(fields);
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
	@Deprecated
	@SuppressWarnings("unchecked")
	public static PropertyModel getReadPropertyXML(HttpServletRequest request, String modelStr) {
		ServletContext servletContext = request.getServletContext();
		Element element = (Element) servletContext.getAttribute("PROPERTY");
		PropertyModel pm = null;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			Element model = models.next();
			String modelName = model.attributeValue("name");
			if (!StringUtils.isEmpty(modelStr) && modelName.equals(modelStr)) {
				pm = new PropertyModel();
				pm.setModel(modelName);
				// 读取指定模块的数据
				Iterator<Element> datas = model.elementIterator();
				Map<String, Map<String, String>> fields = new HashMap<>();
				while (datas.hasNext()) {
					Element data = datas.next();
					String field = data.attributeValue("field");

					Iterator<Element> maps = data.elementIterator();
					Map<String, String> mapData = new HashMap<String, String>();
					while (maps.hasNext()) {
						Element map = maps.next();
						String key = map.attributeValue("key");
						String value = map.attributeValue("value");
						mapData.put(key, value);
					}
					fields.put(field, mapData);
					pm.setDatas(fields);
				}
				break;
			}
		}
		return pm;
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
	@Deprecated
	@SuppressWarnings("unchecked")
	public static PropertyModel getReadPropertyXML(HttpServletRequest request, String modelStr, String fieldStr) {
		ServletContext servletContext = request.getServletContext();
		Element element = (Element) servletContext.getAttribute("PROPERTY");
		PropertyModel pm = null;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			Element model = models.next();
			String modelName = model.attributeValue("name");
			if (!StringUtils.isEmpty(modelStr) && modelName.equals(modelStr)) {
				pm = new PropertyModel();
				pm.setModel(modelName);
				// 读取指定模块的数据
				Iterator<Element> datas = model.elementIterator();
				while (datas.hasNext()) {
					Element data = datas.next();
					String field = data.attributeValue("field");
					if (!StringUtils.isEmpty(fieldStr) && field.equals(fieldStr)) {
						Iterator<Element> maps = data.elementIterator();
						Map<String, String> mapData = new HashMap<String, String>();
						Map<String, Map<String, String>> fields = new HashMap<>();
						while (maps.hasNext()) {
							Element map = maps.next();
							String key = map.attributeValue("key");
							String value = map.attributeValue("value");
							mapData.put(key, value);
						}
						fields.put(field, mapData);
						pm.setDatas(fields);
						break;
					}
				}
				break;
			}
		}
		return pm;
	}

	/**
	 * 用于从内存中读取指定模块名称、字段和关键字的property.xml中的属性配置<br>
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
	@Deprecated
	@SuppressWarnings("unchecked")
	public static PropertyModel getReadPropertyXML(HttpServletRequest request, String modelStr, String fieldStr, String keyStr) {
		ServletContext servletContext = request.getServletContext();
		Element element = (Element) servletContext.getAttribute("PROPERTY");
		PropertyModel pm = null;
		Iterator<Element> models = element.elementIterator();
		while (models.hasNext()) {
			Element model = models.next();
			String modelName = model.attributeValue("name");
			if (!StringUtils.isEmpty(modelStr) && modelName.equals(modelStr)) {
				pm = new PropertyModel();
				pm.setModel(modelName);
				// 读取指定模块的数据
				Iterator<Element> datas = model.elementIterator();
				while (datas.hasNext()) {
					Element data = datas.next();
					String field = data.attributeValue("field");
					if (!StringUtils.isEmpty(fieldStr) && field.equals(fieldStr)) {
						Iterator<Element> maps = data.elementIterator();
						Map<String, String> mapData = new HashMap<String, String>();
						Map<String, Map<String, String>> fields = new HashMap<>();
						while (maps.hasNext()) {
							Element map = maps.next();
							String key = map.attributeValue("key");
							String value = map.attributeValue("value");
							if (!StringUtils.isEmpty(keyStr) && key.equals(keyStr)) {
								mapData.put(key, value);
								break;
							}
						}
						fields.put(field, mapData);
						pm.setDatas(fields);
						break;
					}
				}
				// list.add(pm);
				break;
			}
		}
		return pm;
	}
}
