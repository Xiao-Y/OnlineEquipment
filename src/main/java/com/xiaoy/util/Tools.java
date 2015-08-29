package com.xiaoy.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公用工具类
 * 
 * @author XiaoY
 * @date 2015年8月11日下午5:37:40
 */
public class Tools {
	/**
	 * 用于从request中获取参数值
	 * 
	 * @param request
	 *            当前reuqest
	 * @param name
	 *            参数的名字
	 * @param defaultValue
	 * @return
	 * 
	 * @date 2015年8月11日下午5:30:39
	 */
	public static String getStringParameter(HttpServletRequest request, String name, String defaultValue) {
		String s = request.getParameter(name);
		try {
			if (StringUtils.isEmpty(s)) {
				return defaultValue;
			}
			return s.trim();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 用于从request中获取参数值
	 * 
	 * @param request
	 *            当前reuqest
	 * @param name
	 *            参数的名字
	 * @return
	 * 
	 * @date 2015年8月11日下午5:30:39
	 */
	public static String getStringParameter(HttpServletRequest request, String name) {
		String s = request.getParameter(name);
		try {
			if (StringUtils.isEmpty(s)) {
				return "";
			}
			return s.trim();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 保存上传的文件，返回文件名的字符串以“*”分隔
	 * 
	 * @param imgUrls
	 * @param request
	 * @param realPath
	 * @return
	 */
	public static String uploadFile(MultipartFile[] imgUrls, HttpServletRequest request, String realPath) {
		String path = request.getSession().getServletContext().getRealPath(realPath);

		// 获取图片的分割符
		String imageSplit = Tools.getSystemConfigString(request, "imageUploadSplit");
		StringBuffer buffer = new StringBuffer("");
		for (MultipartFile m : imgUrls) {
			// 获取文件的名称
			String fileName = m.getOriginalFilename();
			if (!StringUtils.isEmpty(fileName)) {
				fileName = Tools.generateFileName(fileName);
				File targetFile = new File(path, fileName);
				// 文件路径不存在
				if (!targetFile.exists()) {
					// 新建文件
					targetFile.mkdirs();
				}
				// 保存
				try {
					m.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 拼接图片的名字，用于保存
				buffer.append(fileName);
				buffer.append(imageSplit);
			}
		}
		String imgUrl = buffer.toString();
		if (!StringUtils.isEmpty(imgUrl)) {
			int index = imgUrl.lastIndexOf(imageSplit);
			imgUrl = imgUrl.substring(0, index);
		}
		return imgUrl;
	}

	/*
	 * public static String appendFileName(MultipartFile[] imgUrls, HttpServletRequest request) { // 获取图片的拼接符 String imageSplit = Tools.getReadPropertiesString(request, "imageUploadSplit"); StringBuffer buffer = new StringBuffer(""); for (MultipartFile m : imgUrls) { // 获取文件的名称 String fileName = m.getOriginalFilename(); if (!StringUtils.isEmpty(fileName)) { fileName = Tools.generateFileName(fileName); // 拼接图片的名字，用于保存 buffer.append(fileName); buffer.append(imageSplit); } } String imgUrl = buffer.toString(); if (!StringUtils.isEmpty(imgUrl)) { int index = imgUrl.lastIndexOf(imageSplit); imgUrl = imgUrl.substring(0, index); } return imgUrl; }
	 */

	/**
	 * 删除图片
	 * 
	 * @param realPath
	 *            图片路径
	 * @param imgUrls
	 *            图片名称字符串，可以为单个
	 * 
	 * @date 2015年8月25日下午12:02:11
	 */
	public static void deleteFile(HttpServletRequest request, String imgUrls, String realPath) {
		// 获取图片的分割符
		String imageSplit = Tools.getSystemConfigString(request, "imageSplit");
		String path = request.getSession().getServletContext().getRealPath(realPath);
		if (!StringUtils.isEmpty(imgUrls)) {
			String[] imageNames = imgUrls.split(imageSplit);
			for (String imageName : imageNames) {
				File file = new File(path, imageName);
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 传入原图名称，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 加载资源文件
	 * 
	 * @param filename
	 * 
	 * @date 2015年8月25日上午9:23:04
	 */
	public static Properties readPropertiesFile(String filename) {
		Properties properties = new Properties();
		try {
			InputStream in = Tools.class.getClassLoader().getResourceAsStream(filename);
			properties.load(in);
			in.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 从内存中，通过key获取系统属性的value
	 * 
	 * @param request
	 * @param key
	 *            关键字
	 * @return value
	 */
	public static String getSystemConfigString(HttpServletRequest request, String key) {
		ServletContext sct = request.getServletContext();
		Properties properties = (Properties) sct.getAttribute("SYSTEM_CONFIG");
		String value = properties.getProperty(key);
		return value;
	}

	/**
	 * 用于从内存中读取property.xml中的属性配置<br>
	 * 如果model不存在，则读取所有<br>
	 * 如果field不存在，则读取所有<br>
	 * 如果key不存在，则读取所有
	 * 
	 * @param request
	 * @param modelStr
	 *            模块的名称
	 * @param fieldStr
	 *            字段名
	 * @param keyStr
	 *            关键字
	 * @return
	 */
	@SuppressWarnings("unchecked")
	// public static PropertyModel getReadPropertyXML(HttpServletRequest request, String modelStr, String fieldStr, String keyStr) {
	public static List<PropertyModel> getReadPropertyXML(Element rootElement, String modelStr, String fieldStr, String keyStr) {
		//ServletContext servletContext = request.getServletContext();
//		Element element = (Element) servletContext.getAttribute("PROPERTY");
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
			// if (!StringUtils.isEmpty(modelStr) && dataElement.attributeValue("field").trim().equals(modelStr)) {
			// // 读取指定字段的数据
			// if (!StringUtils.isEmpty(fieldStr)) {
			// // 读取指定关键字的数据
			// if (!StringUtils.isEmpty(keyStr)) {
			//
			// } else {// 读取所有关键字的数据
			//
			// }
			// } else {// 读取所有字段的
			//
			// }
			// } else {// 读取所有
			//
			// }
		}
		return list;
	}
}
