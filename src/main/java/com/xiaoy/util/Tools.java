package com.xiaoy.util;

import java.io.File;

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
	public static String uploadFile(MultipartFile[] imgUrls, HttpServletRequest request, String realPath)
	{
		String path = request.getSession().getServletContext().getRealPath(realPath);
		StringBuffer buffer = new StringBuffer("");
		for (MultipartFile m : imgUrls)
		{
			// 获取文件的名称
			String fileName = m.getOriginalFilename();
			if (!StringUtils.isEmpty(fileName))
			{
				File targetFile = new File(path, fileName);
				// 文件路径不存在
				if (!targetFile.exists())
				{
					// 新建文件
					targetFile.mkdirs();
				}
				// 保存
				try
				{
					m.transferTo(targetFile);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				// 拼接图片的名字，用于保存
				buffer.append(fileName);
				buffer.append(",");
			}
		}
		String imgUrl = buffer.toString();
		if (!StringUtils.isEmpty(imgUrl))
		{
			int index = imgUrl.lastIndexOf(",");
			imgUrl = imgUrl.substring(0, index);
		}
		return imgUrl;
	}
}
