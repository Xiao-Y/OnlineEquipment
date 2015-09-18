Ext.define("AM.view.RoleList", {
	extend : "Ext.panel.Panel",
	alias : 'widget.roleList',
	layout: 'border',
	items : [{
		region : "north",
		xtype: 'toolbar',
		items : [{
			xtype : 'button',
			text : '添加',
			glyph : 0xf0fe,
			id : 'addRole'
		}, {
			xtype : 'button',
			id : 'delRole',
			glyph : 0xf1f8,
			text : '删除'
		}, {
			xtype : 'button',
			text : '修改',
			glyph : 0xf040,
			id : "editRole"
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryRole"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetRole"
		}, {
			xtype : 'button',
			text : '授权',
			glyph : 0xf007,
			id : "authorize"
		}  ]
	},{
		id : 'roleList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		store : 'RoleStore',// 加载数据
		forceFit : true,//让每列自动填充满表格
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		columns : [ 
		 {
			xtype : 'rownumberer',
			header : '序号',
			width : 50
		}, {
			header : 'ID',
			hidden : true,
			dataIndex : 'id'
		}, {
			header : '角色名称',
			dataIndex : 'roleName'
		}, {
			header : '角色CODE',
			dataIndex : 'roleCode'
		},{
			header : "授权状态",
			dataIndex : 'authorizeStatusName'
		},{
			header : '创建时间',
			dataIndex : 'createTime'
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}],
		dockedItems : [{
			xtype : "pagingtoolbar",
			store : "RoleStore",
			dock : "bottom",
			displayInfo : true
		}]
	}]
});