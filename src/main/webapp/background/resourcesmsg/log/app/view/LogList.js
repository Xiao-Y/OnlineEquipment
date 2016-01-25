Ext.define('AM.view.LogList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.logList',
	layout: 'border',
	border:false,
	items:[{
		xtype : 'panel',
		region: 'north',
		items:[{
			region: 'north',
			xtype: 'toolbar',
			border:false,
			items:[
//				{
//				xtype : 'button',
//				text : '高级查询',
//				glyph : 0xf002,
//				id : "topQueryLog"
//			},
			{
				xtype : 'button',
				text : '详细信息',
				glyph : 0xf0ae,
				id : "lookLog"
			},{
				xtype : 'button',
				text : '查询',
				id : 'queryLog',
				glyph : 0xf002
			},{
				xtype : 'button',
				text : '重置',
				glyph : 0xf021,
				id : "listResetLog"
			} ]
		},{
			region: 'center',
			xtype : 'form',
			id : 'logQueryForm',
			items : [{
				xtype : "fieldset",
				title : "查询条件",
				collapsible : true,// 显示折叠按钮形式
				collapsed : false,// 设置为 true 则将 fieldset 初始化为收缩状态。默认为false
				layout : {
					type: 'table',
       				columns: 4 //每行有几列
				},
				defaults : {
					labelWidth : 90,
					labelAlign : 'right'
				},
				defaultType : 'textfield',
				items : [ {
					fieldLabel : '操作用户',
					xtype : 'textfield',
					name : 'userName'
				},{
					fieldLabel : '运行类名',
					xtype : 'textfield',
					name : 'runClass'
				}, {
					fieldLabel : '操作类型',
					xtype : 'combobox',
					displayField : 'displayField',
					valueField : 'valueField',
					queryMode : 'local',
					store : 'HandleTypeStore',
					name : 'operation',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>",
						maxHeight : 100
					// 最大宽度
					},
					id : 'operation'
				},{
					fieldLabel : '执行时间',
					xtype : 'datefield',
					format : 'Y-m-d',
					invalidText : "{0}不是一个正确的日期格式，如2015-08-06",
					name : 'createTime'
				}]
			}]
		}]
	},{
		id : 'logList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		forceFit : true,//让每列自动填充满表格
		store : 'LogStore',// 加载数据
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		columns : [{
			xtype : 'rownumberer',
			header : '序号',
			width : 50
		}, {
			header : 'ID',
			hidden : true,
			dataIndex : 'id'
		}, {
			header : '模块名',
			dataIndex : 'modal'
		}, {
			header : '功能',
			dataIndex : 'function'
		}, {
			header : '操作',
			dataIndex : 'operation'
		}, {
			header : '用户名',
			dataIndex : 'userId'
		}, {
			header : 'IP地址',
			dataIndex : 'ipAddr'
		}, {
			header : '处理时间',
			dataIndex : 'createTime'
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