Ext.define('AM.view.LogQuery', {
	extend : 'Ext.window.Window',
	alias : "widget.logQuery",
	id : 'logQueryWindow',
	height : 240,
	width : 520,
	layout : 'fit',
	modal:true,//模态框
	title:"高级查询",
	items : [ {
		xtype : 'form',
		id : 'logQueryForm',
		buttonAlign : 'right',
		bodyPadding : 15,
		autoScroll : true,
		layout : 'anchor',
		defaults : {
			xtype : 'textfield',
			labelWidth : 90,
			labelAlign : 'right',
			anchor : '90%'
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
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '查询',
			id : 'queryLog',
			glyph : 0xf002
		}, {
			xtype : 'button',
			text : '重置',
			id : 'resetLog',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancelLog',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});