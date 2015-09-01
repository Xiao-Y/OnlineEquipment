Ext.define('AM.view.RoleAuthorize', {
	extend : 'Ext.window.Window',
	id : 'authorizeWindow',
	alias : "widget.authorizeSave",
	height : 400,
	width : 300,
	layout : 'fit',
	modal:true,//模态框
	items : [ {
		xtype : 'form',//为了显示滑动条
		autoScroll:true,
		items:[{
			xtype : 'treepanel',//添加菜单树
			id : "treepanelId",
			border : false,
			rootVisible : false,// 是否显示根节点
			store : 'RoleTreeStore',
			listeners : {
				'checkchange':function(node, checked) {
					console.info("node : " + node +",checked : "+ checked);
    			}
			}
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