Ext.define('AM.view.MenuQuery', {
	extend : 'Ext.window.Window',
	alias : "widget.menuQuery",
	id : 'menuQueryWindow',
	height : 300,
	width : 400,
	layout : 'fit',
	title:"高级查询",
	items : [ {
		xtype : 'form',
		id : 'menuQueryForm',
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
			fieldLabel : '菜单名称',
			xtype : 'textfield',
			name : 'menuName'
		}, {
			xtype : 'radiogroup',
			fieldLabel : '节点类型',
			id : 'menuType',
			name : 'menuType',
			defaults : {
				flex : 1
			},
			layout : 'hbox',
			items : [ {
				boxLabel : '无',
				name : 'menuType',
				inputValue : '-1',
				checked : true
			}, {
				boxLabel : '树枝节点',
				name : 'menuType',
				inputValue : '0'
			}, {
				boxLabel : '叶子节点',
				name : 'menuType',
				inputValue : '1'
			} ],
			listeners : {
				"change" : function() {
					if (Ext.getCmp("menuType").getValue().menuType == "1") {
						Ext.getCmp("parentId").setDisabled(false);
					} else {
						Ext.getCmp("parentId").setDisabled(true);
					}
				}
			}
		}, {
			fieldLabel : '上级菜单',
			xtype : 'combobox',
			//hidden : true,// 隐藏
			disabled : true,
			displayField : 'menuName',
			valueField : 'id',
			//value : '-1',
			queryMode : 'local',
			store : 'ParentMenuStore',
			name : 'parentId',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			// 最大宽度
			},
			id : 'parentId'
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
		} ]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '查询',
			id : 'queryMenu',
			glyph : 0xf002
		}, {
			xtype : 'button',
			text : '重置',
			id : 'resetMenu',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancelMenu',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});