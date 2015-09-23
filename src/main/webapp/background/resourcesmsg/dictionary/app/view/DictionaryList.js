Ext.define('AM.view.DictionaryList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.dictionaryList',
	layout: 'border',
	border:false,
	items:[{
		region: 'north',
		xtype: 'toolbar',
		border:false,
		items:[{
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryDictionary"
		},{
			xtype : 'button',
			text : '数据字典维护',
			glyph : 0xf0ae,
			id : "maintainDictionary"
		},{
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetDictionary"
		} ]
	},{
		id : 'dictionaryList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		forceFit : true,//让每列自动填充满表格
		store : 'DictionaryStore',// 加载数据
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
			header : '模块名称',
			dataIndex : 'modelName'
		}, {
			header : '字段名称',
			dataIndex : 'fieldName'
		}, {
			header : '显示值',
			dataIndex : 'displayField'
		}, {
			header : '传送值',
			dataIndex : 'valueField'
		}, {
			header : '类型',
			dataIndex : 'notice'
		}, {
			header : '创建时间',
			dataIndex : 'createTime'
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}],
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'DictionaryStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	}]
});