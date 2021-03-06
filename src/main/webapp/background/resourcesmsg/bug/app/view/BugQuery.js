Ext.define('AM.view.BugQuery', {
	extend : 'Ext.window.Window',
	alias : "widget.bugQuery",
	id : 'bugQueryWindow',
	height : 320,
	width : 550,
	layout : 'fit',
	modal:true,
	closable: false,//不显示关闭的叉
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
			fieldLabel : 'BUG标题',
			name : 'title'
		},{
			fieldLabel : 'BUG出现的父模块',
			xtype : 'combobox',
			displayField : 'menuName',
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			editable : true,
			name : 'parentId',
			store : 'ParentMenuStore',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'parentIdQ',
			listeners: {
    	   			//刷新子模块下拉框
        		select:function(combo){
        			Ext.getCmp('childrenIdQ').store.removeAll();
    	   			Ext.getCmp('childrenIdQ').store.load({
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
			editable : true,
			store : 'ChildrenMenuStore',
			name : 'childrenId',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100// 最大宽度
			},
			id : 'childrenIdQ'
		},{
			fieldLabel : '严重程度',
			xtype : 'combobox',
			displayField : 'displayField',
			valueField : 'valueField',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			editable : true,
			store : 'SeverityStore',
			name : 'severity',
			value : '0',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'severityQ'
		},{
			fieldLabel : 'BUG状态',
			xtype : 'combobox',
			displayField : 'displayField',
			valueField : 'valueField',
			id : 'statusQ',
			store : 'StatusStore',
			forceSelection : true,// 所选择的值必须是列表中的值
			editable : true,
			name : 'status',
			value : '0'
		},{
			fieldLabel : '重现规律',
			xtype : 'combobox',
			displayField : 'displayField',
			valueField : 'valueField',
			queryMode : 'local',
			value : '0',
			forceSelection : true,// 所选择的值必须是列表中的值
			editable : true,
			store : 'ReappearStore',
			name : 'reappear',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'reappearQ'
		}, {
			fieldLabel : 'BUG类型',
			xtype : 'combobox',
			displayField : 'displayField',
			valueField : 'valueField',
			queryMode : 'local',
			value : '0',
			forceSelection : true,// 所选择的值必须是列表中的值
			editable : true,
			store : 'BugTypeStore',
			name : 'bugType',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			},
			id : 'bugTypeQ'
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '查询',
			id : 'queryBug',
			glyph : 0xf002
		}, {
			xtype : 'button',
			text : '重置',
			id : 'resetQ',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'hide',
			text : '关闭',
			glyph : 0xf00d
		} ]
	} ]
});