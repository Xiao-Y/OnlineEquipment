Ext.define('AM.view.BugView', {
	extend : 'Ext.window.Window',
	alias : "widget.bugView",
	id : 'bugViewWindow',
	height : 350,
	width : 600,
	layout : 'fit',
	modal:true,//模态框
	items : [ {
		xtype : 'form',
		border:0,
		id : 'bugViewForm',
		buttonAlign : 'right',
		layout : 'fit',
		items : [{
			activeTab: 0,
			xtype: 'tabpanel',
			layout : 'fit',
			bodyPadding : 5,
		    items:[{
		    	title:"详细信息",
				bodyPadding : 5,
				autoScroll : true,
				layout : 'anchor',
				defaults : {
					labelWidth : 90,
					labelAlign : 'right',
					anchor : '90%'
				},
				defaultType : 'displayfield',
				items : [ {
					fieldLabel : 'BUG标题',
					name : 'title'
				},{
					fieldLabel : '父模块',
					name : 'parentName'
				},{
					fieldLabel : '子模块',
					name : 'childrenName'
				},{
					fieldLabel : 'BUG类型',
					name : 'bugType'
				},{
					fieldLabel : 'BUG状态',
					name : 'status'
				},{
					fieldLabel : '严重程度',
					name : 'severity'
				},{
					fieldLabel : '重现规律',
					name : 'reappear'
				},{
					fieldLabel : 'BUG描述',
					name : 'note'
				},{
					fieldLabel : '修改反馈',
					name : 'reviseExplain'
				},{
					fieldLabel : '创建时间',
					name : 'createTime'
				},{
					fieldLabel : '更新时间',
					name : 'updateTime'
				}]
		    },{
		    	title: 'BUG截图信息',
				xtype: 'panel',
				id:'bugViewImagePanel',
				autoScroll:true,
				layout: 'column'
		    }]
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