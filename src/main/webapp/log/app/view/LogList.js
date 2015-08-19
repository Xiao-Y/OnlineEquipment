Ext.define('AM.view.LogList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.logList',
	layout: 'border',
	items:[{
		region: 'north',
		xtype: 'toolbar',
		items:[{
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryLog"
		},{
			xtype : 'button',
			text : '详细信息',
			glyph : 0xf0ae,
			id : "lookLog"
		},{
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetLog"
		} ]
	},{
		id : 'logList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		store : 'LogStore',// 加载数据
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
			header : '处理类',
			dataIndex : 'runClass',
			width : 450
		}, {
			header : '操作',
			dataIndex : 'operation',
			width : 80
		}, {
			header : '用户名',
			dataIndex : 'userId',
			width : 120
		}, {
			header : '处理时间',
			dataIndex : 'createTime',
			width : 140
		}],
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'LogStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	}]
});