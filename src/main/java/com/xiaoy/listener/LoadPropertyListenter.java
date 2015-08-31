package com.xiaoy.listener;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xiaoy.util.PropertyModel;
import com.xiaoy.util.ReadPropertyXML;

/**
 * 用于项目启动时加载属性配置文件
 * 
 * @author XiaoY
 * @date: 2015年8月29日 下午10:43:59
 */
public class LoadPropertyListenter implements ServletContextListener {

	public final static String PROPERTY = "property.xml";

	@Override
	public void contextDestroyed(ServletContextEvent sc) {
		Element rootElement = null;
		SAXReader reader = new SAXReader();
		// InputStream in =
		// Tools.class.getClassLoader().getResourceAsStream(PROPERTY);
		File file = new File("D:\\project\\workspace\\OnlineEquipment\\src\\main\\resources\\property.xml");
		try {
			// Document document = reader.read(in);
			Document document = reader.read(file);
			rootElement = document.getRootElement();
			List<PropertyModel> list = ReadPropertyXML.getReadPropertyXML(rootElement);
			for (PropertyModel pm : list) {
				System.out.println(pm);
			}
		} catch (DocumentException e) {
			System.out.println("属性配置文件加载错误！");
			e.printStackTrace();
		}
		// sc.getServletContext().setAttribute("PROPERTY", rootElement);
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {

	}

	public static void main(String[] args) {
		LoadPropertyListenter l = new LoadPropertyListenter();
		l.contextDestroyed(null);
	}
}
