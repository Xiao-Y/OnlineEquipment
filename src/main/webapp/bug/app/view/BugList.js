Ext.define("AM.view.BugList",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.bugList',
	layout: 'border',
	items:[{
		region: 'north',
		xtype: 'toolbar',
		items:[{
			xtype : 'button',
			text : '添加',
			glyph : 0xf0fe,
			id : 'addBug'
		}, {
			xtype : 'button',
			id : 'delBug',
			glyph : 0xf146,
			text : '删除'
		}, {
			xtype : 'button',
			text : '修改',
			glyph : 0xf0c7,
			id : "editBug"
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryBug"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetBug"
		} ]
	},{
		id : 'bugList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		store : 'BugStore',// 加载数据
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		columns : [ 
		 {
			xtype : 'rownumberer',
			header : '序号',
			width : 40
		}, 
		{
			header : 'ID',
			hidden : true,
			dataIndex : 'id',
			width : 120
		}, {
			header : 'BUG标题',
			dataIndex : 'title',
			width : 130
		}, {
			header : 'BUG状',
			dataIndex : 'status',
			width : 50
		}, {
			header : '父模块',
			dataIndex : 'parentId',
			width : 80
		}, {
			header : '子模块',
			dataIndex : 'childrenId',
			width : 80
		}, {
			header : '严重程度',
			dataIndex : 'severity',
			width : 120
		}, {
			header : '重现规律',
			dataIndex : 'reappear',
			width : 80
		}, {
			header : 'BUG类型',
			dataIndex : 'bugType',
			width : 80
		}, {
			header : '优先级',
			dataIndex : 'priority',
			width : 80
		}, {
			header : '创建时间',
			dataIndex : 'createTime',
			width : 140
		}, {
			header : '更新时间',
			dataIndex : 'updateTime',
			width : 140
		}, {
			header : '备注',
			dataIndex : 'remark',
			width : 160
		} ],
		// 一个或者一系列组件作为挂靠组件被添加到panel中
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'BugStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	}]
});