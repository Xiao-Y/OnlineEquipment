Ext.define('AM.view.NoticeView', {
	extend : 'Ext.window.Window',
	alias : "widget.noticeView",
	id : 'noticeViewWindow',
	resizable   : false,//是否可调整 
	maximizable : true, //显示最大化
	height : 350,
	width : 600,
	layout : 'fit',
	modal:true,//模态框
	title:"详细信息",
	items : [ {
		xtype : 'form',
		id : 'noitceViewForm',
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
			fieldLabel : '公告标题',
			name : 'noticeTit'
		},{
			fieldLabel : '公告内容',
			name : 'notice'
		},{
			fieldLabel : '公告人',
			name : 'noticeName'
		},{
			fieldLabel : '创建时间',
			name : 'createTime'
		}, {
			fieldLabel : '更新时间',
			name : 'updateTime'
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