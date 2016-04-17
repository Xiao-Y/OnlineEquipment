
系统是以模块进行划分的，每个模块中都遵循MVC结构。controller为控制器层、service为业务逻辑层、dao为数据库交互层。
前端使用的是ExtJs的mvc模式，
结构：
home
	app
		controller
		model
		store
		view
		app.js
	index.jsp

系统使用到的技术：
后端：SpringMVC-4.0.5、Hibernate-4.2.0、Sping-4.0.5、Shiro-1.2.2
前端：Extjs-5.1
字体样式：FontAwesome-4.2.0
服务容器：jetty-8.1.14
数据库：Mysql-5.1

系统后台入口：
http://localhost:9080/OnlineEquipment/background/home/index
系统前台入口：
http://localhost:9080/OnlineEquipment/foreground/index


1.每个入口jsp中都要引入公用js和css

<jsp:include page="/resource/index.html"/>

----------------------------------------------------

2.使用font-awesome:

在app.js中：

//启用图标
Ext.setGlyphFontFamily('FontAwesome'); //Font Awesome图标

方式1：glyph : 0xf013,

方式2：glyph : "xf0c7@FontAwesome",

注意：两种方式不同。
图标：
http://fortawesome.github.io/Font-Awesome/cheatsheet/
http://fontawesome.dashgame.com/

------------------------------------------------------------