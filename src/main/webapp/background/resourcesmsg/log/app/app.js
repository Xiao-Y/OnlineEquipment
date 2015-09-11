Ext.onReady(function() {
	// 初始化
	Ext.QuickTips.init();

	//启用图标
	Ext.setGlyphFontFamily('FontAwesome'); //Font Awesome图标
	
	Ext.Loader.setConfig({
		enabled : true
	});

	Ext.application({
		name : 'AM',// 应用的名字
		appFolder : 'app',// 应用的目录
		launch : function() {// 当前页面加载完成执行的函数
			Ext.create('Ext.container.Viewport', {
				layout : 'fit',
				items : [ {
					xtype : 'logList'//使用的别名
				}]
			});
		},
		controllers:['LogController']
	});
})