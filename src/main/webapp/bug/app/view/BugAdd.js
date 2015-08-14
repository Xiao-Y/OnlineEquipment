Ext.define('AM.view.BugAdd', {
	extend : 'Ext.window.Window',
	alias : "widget.bugAdd",
	id : 'bugAddWindow',
	height : 500,
	width : 800,
	modal:true,//模态框
	layout : 'fit',
	items : [ {
		xtype : 'form',
		buttonAlign : 'right',
		bodyPadding : 10,
		id : "bugAddForm",
		autoScroll : true,
		layout : 'anchor',
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
			xtype:'panel',
			layout : "column",
        	border:0,
        	defaults : {
				labelWidth : 140,
				labelAlign : 'right',
				anchor : '90%'
			},
        	bodyPadding : 5,
        	items:[{
				fieldLabel : 'BUG出现的父模块',
				xtype : 'combobox',
				displayField : 'menuName',
				valueField : 'id',
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
				id : 'parentId',
				listeners: {
	        		select:function(combo){
	        			Ext.getCmp('childrenId').store.removeAll();
	    	   			Ext.getCmp('childrenId').store.load({
	    	   				params : {
	    	   					parentId:combo.getValue()
	    	   				}
	    	   			});//刷新子模块下拉框
	        		}
	        	}
			},{
				fieldLabel : 'BUG出现的子模块',
				xtype : 'combobox',
				displayField : 'menuName',
				allowBlank : false,
				valueField : 'id',
				queryMode : 'local',
				forceSelection : true,// 所选择的值必须是列表中的值
				store : 'ChildrenMenuStore',
				name : 'childrenId',
				listConfig : {// 下拉列表的样式
					emptyText : "<font color='red'>没有找到匹配项</font>",
					maxHeight : 100// 最大宽度
				},
				id : 'childrenId'
			}]
		},{
			xtype:'panel',
			layout : "column",
        	border:0,
        	defaults : {
				labelWidth : 140,
				labelAlign : 'right',
				anchor : '90%'
			},
        	bodyPadding : 5,
        	items:[{
				fieldLabel : '严重程度',
				xtype : 'combobox',
				displayField : 'severityName',
				allowBlank : false,
				valueField : 'severity',
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
				},
				id : 'severity'
			},{
				fieldLabel : 'BUG状态',
				xtype : 'combobox',
				displayField : 'statusName',
				valueField : 'status',
				allowBlank : false,
				queryMode : 'local',
				id : 'status',
				forceSelection : true,// 所选择的值必须是列表中的值
				store : 'StatusStore',
				editable : false,
				name : 'status',
				value : '2'
			}]
		},{
			xtype:'panel',
			layout : "column",
        	border:0,
        	defaults : {
				labelWidth : 140,
				labelAlign : 'right',
				anchor : '90%'
			},
        	bodyPadding : 5,
        	items:[{
				fieldLabel : '重现规律',
				xtype : 'combobox',
				displayField : 'reappearName',
				allowBlank : false,
				valueField : 'reappear',
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
				},
				id : 'reappear'
			}, {
				fieldLabel : 'BUG类型',
				xtype : 'combobox',
				displayField : 'bugTypeName',
				allowBlank : false,
				valueField : 'bugType',
				queryMode : 'local',
				value : '6',
				editable : false,
				forceSelection : true,// 所选择的值必须是列表中的值
				store : 'BugTypeStore',
				name : 'bugType',
				listConfig : {// 下拉列表的样式
					emptyText : "<font color='red'>没有找到匹配项</font>",
					maxHeight : 100
				// 最大宽度
				},
				id : 'bugType'
			}
//			, {
//				fieldLabel : 'BUG优先级',
//				xtype : 'combobox',
//				displayField : 'priorityName',
//				allowBlank : false,
//				valueField : 'priority',
//				value : '2',
//				queryMode : 'local',
//				forceSelection : true,// 所选择的值必须是列表中的值
//				store : 'PriorityStore',
//				name : 'priority',
//				listConfig : {// 下拉列表的样式
//					emptyText : "<font color='red'>没有找到匹配项</font>",
//					maxHeight : 100
//				// 最大宽度
//				},
//				id : 'priority'
//			}
			]
		}, {
			fieldLabel : 'BUG标题',
			name : 'title',
			allowBlank : false
		}, {
			xtype : "htmleditor",
			width : 500,
			height : 300,
			fontFamilies : [ "宋体", "黑体", "楷书", "行书" ],// 修改编辑用的字体
			fieldLabel : 'BUG描述',
			defaultLinkValue : "http://www.",// 修改编辑器中link的默认值，默认值为：http://
			name : "note"
		} , {
			fieldLabel : '修改反馈',
			xtype : "htmleditor",
			width : 500,
			height : 300,
			fontFamilies : [ "宋体", "黑体", "楷书", "行书" ],// 修改编辑用的字体
			defaultLinkValue : "http://www.",// 修改编辑器中link的默认值，默认值为：http://
			name : "reviseExplain"
		} ]
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