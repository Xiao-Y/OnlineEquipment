Ext.define('AM.view.BugAdd', {
	extend : 'Ext.window.Window',
	alias : "widget.bugAdd",
	id : 'bugAddWindow',
	height : 360,
	width : 570,
	modal:true,// 模态框
	layout : 'fit',
	items : [ {
		xtype : 'form',
		buttonAlign : 'right',
		border:0,
		id : "bugAddForm",
		layout : 'fit',
	    items:[{
	    	bodyPadding : 5,
			xtype: 'tabpanel',
			layout : 'fit',
		    activeTab: 0,
		    items:[{
				title:"BUG基本信息",
				bodyPadding : 5,
				layout: 'anchor',
		    	defaults : {
					labelWidth : 140,
					labelAlign : 'right',
					anchor : '90%'
				},
				defaultType : 'textfield',
				items : [{
					xtype : 'hiddenfield',
					name : 'id'
				},{
					fieldLabel : 'BUG出现的父模块',
					xtype : 'combobox',
					displayField : 'menuName',
					valueField : 'id',
					id : 'parentId',
					queryMode : 'local',
					allowBlank : false,
					forceSelection : true,// 所选择的值必须是列表中的值
					name : 'parentId',
					store : 'ParentMenuStore',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>",
						maxHeight : 100
					// 最大宽度
					},
					listeners: {
		        		select:function(combo){
		        			Ext.getCmp('childrenId').store.removeAll();
		    	   			Ext.getCmp('childrenId').store.load({
		    	   				params : {
		    	   					parentId:combo.getValue()
		    	   				}
		    	   			});// 刷新子模块下拉框
		        		}
		        	}
				},{
					fieldLabel : 'BUG出现的子模块',
					xtype : 'combobox',
					displayField : 'menuName',
					allowBlank : false,
					valueField : 'id',
					id : 'childrenId',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'ChildrenMenuStore',
					name : 'childrenId',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>",
						maxHeight : 100// 最大宽度
					}
				},{
					fieldLabel : '严重程度',
					xtype : 'combobox',
					displayField : 'displayField',
					allowBlank : false,
					valueField : 'valueField',
					id : 'severity',
					queryMode : 'local',
					value : '2',
					forceSelection : true,// 所选择的值必须是列表中的值
					editable : false,
					store : 'SeverityStore',
					name : 'severity',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>",
						maxHeight : 100
					// 最大宽度
					}
				},{
					fieldLabel : 'BUG状态',
					xtype : 'combobox',
					displayField : 'displayField',
					valueField : 'valueField',
					allowBlank : false,
					queryMode : 'local',
					id : 'status',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'StatusStore',
					editable : false,
					name : 'status',
					value : '2'
				},{
					fieldLabel : '重现规律',
					xtype : 'combobox',
					displayField : 'displayField',
					allowBlank : false,
					valueField : 'valueField',
					id : 'reappear',
					queryMode : 'local',
					editable : false,
					value : '3',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'ReappearStore',
					name : 'reappear',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>",
						maxHeight : 100
					// 最大宽度
					}
				}, {
					fieldLabel : 'BUG类型',
					xtype : 'combobox',
					displayField : 'displayField',
					allowBlank : false,
					valueField : 'valueField',
					queryMode : 'local',
					value : '6',
					editable : false,
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'BugTypeStore',
					id : 'bugType',
					name : 'bugType',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>",
						maxHeight : 100
					// 最大宽度
					}
				}, {
					fieldLabel : 'BUG标题',
					name : 'title',
					allowBlank : false
		    	}]
			}, {
				title:"BUG描述",
				xtype : "htmleditor",
				fontFamilies : [ "宋体", "黑体", "楷书", "行书" ],// 修改编辑用的字体
				defaultLinkValue : "http://www.",// 修改编辑器中link的默认值，默认值为：http://
				name : "note"
			} , {
				title:"修改反馈",
				xtype : "htmleditor",
				fontFamilies : [ "宋体", "黑体", "楷书", "行书" ],// 修改编辑用的字体
				defaultLinkValue : "http://www.",// 修改编辑器中link的默认值，默认值为：http://
				name : "reviseExplain"
			},{
				title:"BUG截图",
				bodyPadding : 5,
				layout: 'anchor',
				defaults : {
					labelWidth : 80,
					labelAlign : 'right',
					name: 'imgUrls',
					fieldLabel: '上传截图',
	        		buttonText: '上传',
					anchor : '90%'
				},
				defaultType : 'filefield',
				//添加6个上传控件，一个提示信息
				items:[{},{},{},{},{},{},{
					xtype: 'displayfield',
					fieldLabel: '提示',
					value: "<font color='red'>注意，修改图片时，将会覆盖历史图片！</font>"
				}]
			} ]
	    }]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '保存',
			id : 'saveBug',
			glyph:0xf0c7
		}, {
			xtype : 'button',
			text : '重置',
			id : 'resetBug',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancelBug',
			text : '关闭',
			glyph : 0xf00d
		} ]
	} ]
});