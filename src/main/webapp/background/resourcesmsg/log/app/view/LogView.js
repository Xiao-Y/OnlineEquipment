Ext.define('AM.view.LogView', {
	extend : 'Ext.window.Window',
	alias : "widget.logView",
	id : 'logViewWindow',
	resizable   : false,//是否可调整 
	maximizable : true, //显示最大化
	height : 350,
	width : 600,
	layout : 'fit',
	modal:true,//模态框
	title:"详细信息",
	items : [ {
		xtype : 'form',
		id : 'logViewForm',
		buttonAlign : 'right',
		bodyPadding : 15,
		autoScroll : true,
		layout : 'anchor',
		defaults : {
			labelWidth : 90,
			labelAlign : 'right',
			anchor : '90%'
		},
		defaultType : 'displayfield',
		items : [ {
			fieldLabel : '操作用户',
			name : 'userName'
		},{
			fieldLabel : '运行类名',
			name : 'runClass'
		},{
			fieldLabel : '操作类型',
			name : 'operation'
		},{
			fieldLabel : '执行时间',
			name : 'createTime'
		}, {
			fieldLabel : '操作内容',
			name : 'content'
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			id : 'cancel',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});