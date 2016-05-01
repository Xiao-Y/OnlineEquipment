Ext.define('AM.view.MenuAdd', {
	extend : 'Ext.window.Window',
	alias : "widget.menuAdd",
	id : 'menuAddWindow',
	height : 300,
	width : 500,
	modal:true,//模态框
	layout : 'fit',
	items : [ {
		xtype : 'form',
		buttonAlign : 'right',
		bodyPadding : 15,
		id : "menuAddForm",
		autoScroll : true,
		layout : 'anchor',
		defaults : {
			labelWidth : 130,
			labelAlign : 'right',
			anchor : '90%'
		},
		defaultType : 'textfield',
		items : [ {
			xtype : 'hiddenfield',
			name : 'id'
		}, {
			fieldLabel : '菜单名称',
			name : 'menuName',
			allowBlank : false
		}, {
			fieldLabel : '菜单CODE',
			name : 'menuCode',
			allowBlank : false
		}, {
			fieldLabel : '请求路径',
			name : 'menuUrl'
		},{
			fieldLabel : '节点类型',
			xtype : 'combobox',
			// hidden : true,// 隐藏
			displayField : 'displayField',
			valueField : 'valueField',
			id : 'menuType',
			queryMode : 'local',
			store : 'MenuTypeStore',
			name : 'menuType',
			forceSelection : true,// 所选择的值必须是列表中的值
			value : '1',//叶子节点
			listeners : {
				"change" : function() {
					if (Ext.getCmp("menuType").getValue() == "0") {
						Ext.getCmp("parentId").setDisabled(true);
					} else {
						Ext.getCmp("parentId").setDisabled(false);
					}
				}
			}
		}, {
			fieldLabel : '上级菜单',
			xtype : 'combobox',
			 //hidden : true,// 隐藏
			displayField : 'menuName',
			allowBlank : false,
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'ParentMenuStore',
			name : 'parentId',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			// 最大宽度
			},
			id : 'parentId'
		}, {
			fieldLabel : '排序号',
			name : 'seq',
			maxLength : 4,
			xtype : 'numberfield',// 数值类型
			allowDecimals : false,// 是否允许小数
			// decimalPrecision: 2, // 小数位精度
			allowNegative : false, // 是否允许负数
			allowBlank : true,// 是否允许为空
			minValue : 0,
			maxValue : 9999
		}, {
			fieldLabel : '备注',
			name : 'remark'
		} ]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '保存',
			id : 'saveMenu',
			glyph:0xf0c7
		}, {
			xtype : 'button',
			text : '重置',
			id : 'reset',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancel',
			text : '关闭',
			glyph : 0xf00d
		} ]
	} ]
});