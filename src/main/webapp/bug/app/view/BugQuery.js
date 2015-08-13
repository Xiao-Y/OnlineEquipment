Ext.define('AM.view.BugQuery', {
	extend : 'Ext.window.Window',
	alias : "widget.bugQuery",
	id : 'bugQueryWindow',
	height : 300,
	width : 400,
	layout : 'fit',
	title:"高级查询",
	items : [ {
		xtype : 'form',
		buttonAlign : 'right',
		bodyPadding : 10,
		id : "bugQueryForm",
		autoScroll : true,
		layout : 'anchor',
		defaults : {
			labelWidth : 140,
			labelAlign : 'right',
			anchor : '90%'
		},
		defaultType : 'textfield',
		items : [{
			fieldLabel : 'BUG出现的父模块',
			xtype : 'combobox',
			displayField : 'menuName',
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			name : 'parentId',
			store : 'ParentMenuStore',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'parentId',
			listeners: {
    	   			//刷新子模块下拉框
        		select:function(combo){
        			Ext.getCmp('childrenId').store.removeAll();
    	   			Ext.getCmp('childrenId').store.load({
    	   				params : {
    	   					parentId:combo.getValue()
    	   				}
    	   			});
        		}
        	}
		},{
			fieldLabel : 'BUG出现的子模块',
			xtype : 'combobox',
			displayField : 'menuName',
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
		},{
			fieldLabel : '严重程度',
			xtype : 'combobox',
			displayField : 'severityName',
			valueField : 'severity',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'SeverityStore',
			name : 'severity',
			value : '0',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'severity'
		},{
			fieldLabel : 'BUG状态',
			xtype : 'combobox',
			displayField : 'statusName',
			valueField : 'status',
			id : 'status',
			store : 'StatusStore',
			editable : false,
			name : 'status',
			value : '0'
		},{
			fieldLabel : '重现规律',
			xtype : 'combobox',
			displayField : 'reappearName',
			valueField : 'reappear',
			queryMode : 'local',
			value : '0',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'ReappearStore',
			name : 'reappear',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'reappear'
		}, {
			fieldLabel : 'BUG类型',
			xtype : 'combobox',
			displayField : 'bugTypeName',
			valueField : 'bugType',
			queryMode : 'local',
			value : '0',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'BugTypeStore',
			name : 'bugType',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'bugType'
		}, {
			fieldLabel : 'BUG标题',
			name : 'title'
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '查询',
			id : 'queryBug',
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