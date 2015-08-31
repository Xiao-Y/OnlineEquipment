package com.xiaoy.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 用于在服务启动时，加载系统配置文件
 * 
 * @author XiaoY
 * @date: 2015年8月25日 下午9:13:57
 */
public class LoadSystemConfigListener implements ServletContextListener {
	/**
	 * 系统配置文件
	 */
	public final static String SYSTEM_CONFIG = "systemConfig.properties";

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		Properties properties = new Properties();
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(SYSTEM_CONFIG);
			properties.load(in);
			in.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.getServletContext().setAttribute("SYSTEM_CONFIG", properties);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sc) {

	}
}
