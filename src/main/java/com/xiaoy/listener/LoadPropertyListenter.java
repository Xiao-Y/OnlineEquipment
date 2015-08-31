package com.xiaoy.listener;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用于项目启动时加载属性配置文件
 * 
 * @author XiaoY
 * @date: 2015年8月29日 下午10:43:59
 */
public class LoadPropertyListenter implements ServletContextListener {

	public final static String PROPERTY = "property.xml";

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		Element rootElement = null;
		try {
			SAXReader reader = new SAXReader();
			InputStream in = getClass().getClassLoader().getResourceAsStream(PROPERTY);
			Document document = reader.read(in);
			rootElement = document.getRootElement();
			in.close();
		} catch (DocumentException | IOException e) {
			System.out.println("属性配置文件加载错误！");
			e.printStackTrace();
		}
		sc.getServletContext().setAttribute("PROPERTY", rootElement);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sc) {

	}
}
