Ext.define('AM.view.RoleAuthorize', {
	extend : 'Ext.window.Window',
	//alias : "widget.authorize",
	id : 'authorizeWindow',
	height : 500,
	width : 400,
	layout : 'fit',
	modal:true,//模态框
	items : [ {
		xtype : 'panel',//为了显示滑动条
		autoScroll:true,
		bodyPadding : 3,
		items:[{
			title : "选择权限",
			xtype : 'treepanel',//添加菜单树
			border : false,
			// 是否显示根节点
			rootVisible : true,
			// 数据集
			store : 'RoleTreeStore'
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '保存',
			id : 'saveAuthorize',
			glyph : 0xf002
		}, {
			xtype : 'button',
			id : 'cancelAuthorize',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});