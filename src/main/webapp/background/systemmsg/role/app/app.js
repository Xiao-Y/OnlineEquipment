Ext.onReady(function(){
	//初始化
	Ext.QuickTips.init();
	//启用图标
	Ext.setGlyphFontFamily("FontAwesome");
	//开启动态加载机制
	Ext.Loader.setConfig({
		enabled : true
	});
	Ext.application({
		name : "AM",
		appFolder : "app",
		launch : function(){
			Ext.create("Ext.container.Viewport",{
				layout : "fit",
				items : [{
					xtype : "roleList"
				}]
			});
		},
		controllers : ["RoleController"]
	});
});
