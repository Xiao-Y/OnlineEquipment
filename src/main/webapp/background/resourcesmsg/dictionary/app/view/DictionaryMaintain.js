Ext.define("AM.view.DictionaryMaintain",{
	extend : "Ext.window.Window",
	alias : "widget.dictionaryMaintain",
	id : "dictionaryMaintainWindow",
	modal : true,
	resizable : false,//是否可调整 
	width : 570,
	height : 420,
	layout : "fit",
	items : [{
		id : "dictionaryMaintainForm",
		border : false,
		items : [{
			xtype : "panel",
			bodyPadding : 5,
			items : [{
				layout : "column",
				border : false,
				defaults : {
					labelWidth : 80,
					labelAlign : 'right',
					columnWidth : .5
				},
				defaultType : 'textfield',
				bodyPadding : 3,
				items : [{
					fieldLabel : '模块名',
					xtype : 'combobox',
					displayField : 'roleName',
					allowBlank : false,
					multiSelect : true,//允许多选
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					//store : 'RoleStore',
					//name : 'roleId',
					//id : 'roleId',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					}
				},{
					fieldLabel : '新模块名',
					name : 'name'
				}]
			},{
				layout : "column",
				border : false,
				defaults : {
					labelWidth : 80,
					labelAlign : 'right',
					columnWidth : .5
				},
				defaultType : 'textfield',
				bodyPadding : 3,
				items : [{
					fieldLabel : '字段名',
					xtype : 'combobox',
					displayField : 'roleName',
					allowBlank : false,
					multiSelect : true,//允许多选
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					//store : 'RoleStore',
					//name : 'roleId',
					//id : 'roleId',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					}
				},{
					fieldLabel : '新字段名',
					name : 'name'
				}]
			}]
		},{
			xtype : 'panel',
			border : false,
			items : [{
				id : 'keyValueList',
				region: 'center',
				xtype: 'gridpanel',
				autoScroll:true,
				forceFit : true,//让每列自动填充满表格
				store : 'DictionaryStore',// 加载数据
				selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
					mode: 'SINGLE'//单选模式
				}),
				columns : [{
					xtype : 'rownumberer',
					header : '序号',
					width : 50
				}, {
					header : '传送值',
					dataIndex : 'id'
				}, {
					header : '显示值',
					dataIndex : 'modelName'
				}],
				dockedItems : [ {
					xtype : 'pagingtoolbar',// 分页组件
					store : 'DictionaryStore',// 数据
					dock : 'top',// 指定位置top,bottom
					displayInfo : true// 展示信息
				}]
			}]
		}]
	}],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ {
			xtype : 'button',
			text : '添加键值',
			glyph:0xf0fe
		},{
			xtype : 'button',
			text : '删除键值',
			glyph:0xf1f8
		},{
			xtype : 'button',
			text : '删除模块',
			glyph:0xf1f8
		},{
			xtype : 'button',
			text : '删除字段',
			id : 'EditUserImg',
			glyph:0xf1f8
		},'->', {
			xtype : 'button',
			text : '保存',
			id : 'saveUser',
			glyph:0xf0c7
		}, {
			xtype : 'button',
			id : 'cancel',
			text : '关闭',
			glyph : 0xf00d
		} ]
	} ]
});