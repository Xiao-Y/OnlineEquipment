Ext.define('AM.view.NoticeAdd', {
	extend : 'Ext.window.Window',
	alias : "widget.noticeAdd",
	id : 'noticeAddWindow',
	height : 330,
	width : 600,
	layout : 'fit',
	modal:true,//模态框
	title:"添加公告",
	items : [ {
		xtype : 'form',
		id : 'noticeAddForm',
		buttonAlign : 'right',
		bodyPadding : 15,
		autoScroll : true,
		layout : 'anchor',
		border:false,
		defaults : {
			xtype : 'textfield',
			labelWidth : 70,
			labelAlign : 'right',
			allowBlank : false,
			anchor : '90%'
		},
		defaultType : 'textfield',
		items : [{
			xtype : 'hiddenfield',
			name : 'id'
		}, {
			xtype : 'hiddenfield',
			name : 'createTime',
			value : new Date()
		}, {
			fieldLabel : '公告标题',
			xtype : 'textfield',
			name : 'noticeTit'
		},{
			fieldLabel : '公告内容',
			xtype : "htmleditor",
			fontFamilies : [ "宋体", "黑体", "楷书", "行书" ],// 修改编辑用的字体
			defaultLinkValue : "http://www.",// 修改编辑器中link的默认值，默认值为：http://
			name : "notice"
		} ]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '保存',
			id : 'saveNotice',
			glyph : 0xf002
		}, {
			xtype : 'button',
			text : '重置',
			id : 'reset',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancel',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});