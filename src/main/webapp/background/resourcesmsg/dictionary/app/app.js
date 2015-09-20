Ext.onReady(function() {
	// 初始化
	Ext.QuickTips.init();
	
	// 设置图标字体文件，只有设置了以后才能用glyph属性
	Ext.setGlyphFontFamily('FontAwesome'); 
	
	// //开启动态加载
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
					xtype : 'dictionaryList'//使用的别名
				}]
			});
		},
		controllers:['DictionaryController']//添加控制器
	});
})