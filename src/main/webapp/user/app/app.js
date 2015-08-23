Ext.onReady(function(){
	//初始化
	Ext.QuickTips.init();
	//添加使用字体, Font Awesome图标
	Ext.setGlyphFontFamily("FontAwesome");
	//开启动态加载机制
	Ext.Loader.setConfig({
		enabled : true
	});
	
	Ext.application({
		name : "AM",
		appFolder : "app",
		launch : function(){// 当前页面加载完成执行的函数
			Ext.create("Ext.container.Viewport", {
				layout : "fit",
				items : [{
					xtype : "userList"
				}]
			});
		},
		controllers : ["UserController"]
	});
});