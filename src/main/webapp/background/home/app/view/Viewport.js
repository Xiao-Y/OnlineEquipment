Ext.define('AM.view.Viewport',{
	extend : 'Ext.container.Viewport',
	alias : 'widget.homeViewport',
	// 布局方式：border
	layout : 'border',
	border:false,
	items : [{
		//title : 'ExtJS案例',
		region : 'north',
		height : 60,
		border:false,
		//html : '<br><center><font size=5>MVC模式实现的ExtJS案例</font><br><font size=2>by XiaoY</font></center>'	
		html : '<img src="../../resource/images/headbg.jpg">'	
	}, {
		title : '功能菜单',
		region : 'west',
		width : 240,
		split : true,//自由调动
		collapsible: true,//显示折叠
		xtype : 'panel',//为了显示滑动条
		border:false,
		autoScroll:true,
		bodyPadding : 3,
		items:[{//更换主题
	        xtype: 'combobox',
	        width: 200,
	        id:"themeCombo",
	        labelWidth: 50,
	        fieldLabel: '主题',
	        displayField: 'displayField',
	        valueField: 'valueField',
	        queryMode: 'local',
	        store: "ThemeStore"
		},{
			border:false,
			xtype : 'menutree'//添加菜单树
		}]
	}, {
		id : 'mainContent',
		border:false,
		//title : '主题内容显示',
		xtype:'tabpanel',
		region : 'center'
	},{
        xtype:'toolbar',
        region:'south',
        border:false,
        ui: 'footer',
        margin: '7 0 0 0',
        items: ['就绪', '->', '&copy; 2015 By XiaoY制作']
    } ]
});
