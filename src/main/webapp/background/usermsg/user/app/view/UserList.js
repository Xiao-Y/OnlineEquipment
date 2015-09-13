Ext.define("AM.view.UserList", {
	extend : "Ext.panel.Panel",
	alias : 'widget.userList',
	layout: 'border',
	border:false,
	items : [{
		region : "north",
		xtype: 'toolbar',
		border:false,
		items : [{
			xtype : 'button',
			text : '添加',
			glyph : 0xf0fe,
			id : 'addUser'
		}, {
			xtype : 'button',
			id : 'delUser',
			glyph : 0xf146,
			text : '删除'
		}, {
			xtype : 'button',
			text : '修改',
			glyph : 0xf0c7,
			id : "editUser"
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryUser"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetUser"
		} ]
	},{
		id : 'userList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		store : 'UserStore',// 加载数据
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
			header : '用户名',
			dataIndex : 'username'
		}, {
			header : '出生年月',
			dataIndex : 'birthday'
		}, {
			header : '地址',
			dataIndex : 'address',
			width : 240
		}, {
			hidden : true,
			dataIndex : 'province'
		}, {
			hidden : true,
			dataIndex : 'city'
		}, {
			hidden : true,
			dataIndex : 'area'
		}, {
			header : '角色名称',
			dataIndex : 'roleName'
		}, {
			header : '创建时间',
			dataIndex : 'createTime'
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}],
		dockedItems : [{
			xtype : "pagingtoolbar",
			store : "UserStore",
			dock : "bottom",
			displayInfo : true
		}]
	}]
});