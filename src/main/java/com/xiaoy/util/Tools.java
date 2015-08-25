package com.xiaoy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公用工具类
 * 
 * @author XiaoY
 * @date 2015年8月11日下午5:37:40
 */
public class Tools {
	
	/**
	 * 系统配置文件
	 */
	public final static String SYSTEM_CONFIG = "systemConfig.properties";

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
	 * 保存上传的文件，返回文件名的字符串以“，”分隔
	 * 
	 * @param imgUrls
	 * @param request
	 * @param realPath
	 * @return
	 */
	public static String uploadFile(MultipartFile[] imgUrls, HttpServletRequest request, String realPath) {
		String path = request.getSession().getServletContext().getRealPath(realPath);
		//加载配置文件
		Properties properties = Tools.readPropertiesFile(SYSTEM_CONFIG);
		//获取文件的分割符
		String imageSplit = properties.getProperty("imageSplit");
		
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
	  * 读取资源文件
	  * 
	  * @param filename
	  *
	  * @date 2015年8月25日上午9:23:04
	  */
    public static Properties readPropertiesFile(String filename)  
    {  
        Properties properties = new Properties();  
        try  
        {  
            InputStream inputStream = new FileInputStream(filename);  
            properties.load(inputStream);  
            inputStream.close(); //关闭流  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
       return properties;
    } 
}
