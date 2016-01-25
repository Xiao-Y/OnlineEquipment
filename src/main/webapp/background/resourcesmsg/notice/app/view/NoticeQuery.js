Ext.define('AM.view.NoticeQuery', {
	extend : 'Ext.window.Window',
	alias : "widget.noticeQuery",
	id : 'noticeQueryWindow',
	height : 280,
	width : 450,
	layout : 'fit',
	closable: false,//不显示关闭的叉
	modal:true,//模态框
	title:"高级查询",
	items : [ {
		xtype : 'form',
		id : 'noticeQueryForm',
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
			fieldLabel : '公告人',
			xtype : 'textfield',
			name : 'noticeName'
		},{
			fieldLabel : '公告标题',
			xtype : 'textfield',
			name : 'noticeTit'
		},{
			fieldLabel : '公告内容',
			xtype : 'textfield',
			name : 'notice'
		},{
			fieldLabel : '创建时间',
			xtype : 'datefield',
			format : 'Y-m-d',
			invalidText : "{0}不是一个正确的日期格式，如2015-08-06",
			name : 'createTime'
		},{
			fieldLabel : '更新时间',
			xtype : 'datefield',
			format : 'Y-m-d',
			invalidText : "{0}不是一个正确的日期格式，如2015-08-06",
			name : 'updateTime'
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '查询',
			id : 'queryNotice',
			glyph : 0xf002
		}, {
			xtype : 'button',
			text : '重置',
			id : 'reset',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'hide',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});