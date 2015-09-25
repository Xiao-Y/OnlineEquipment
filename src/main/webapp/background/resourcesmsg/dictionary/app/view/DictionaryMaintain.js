Ext.define("AM.view.DictionaryMaintain",{
	extend : "Ext.window.Window",
	alias : "widget.dictionaryMaintain",
	id : "dictionaryMaintainWindow",
	modal : true,
	resizable : false,//是否可调整 
	width : 600,
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
					columnWidth : .33
				},
				defaultType : 'textfield',
				bodyPadding : 3,
				items : [{
					fieldLabel : '模块名',
					xtype : 'combobox',
					displayField : 'modelName',
					allowBlank : false,
					valueField : 'modelCode',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'ModelBoxStore',
					name : 'modelCode',
					id : 'modelCode',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					},
					listeners: {
		        		select:function(combo){
		        			var moCode = combo.getValue();
		        			//当为空（新增）时去掉只读
		        			if(moCode == ""){
		        				Ext.getCmp("modelCodeReadOnly").setReadOnly(false);
		        			}else{
		        				Ext.getCmp("modelCodeReadOnly").setReadOnly(true);
		        			}
		        			Ext.getCmp("modelCodeReadOnly").setValue(moCode);
		        			Ext.getCmp('fieldCode').getStore().removeAll();
		        			//级联字段选项
		    	   			Ext.getCmp('fieldCode').getStore().load({
		    	   				params : {
		    	   					fieldCode:combo.getValue()
		    	   				},
		    	   				//在已有的store中添加一列
		    	   				callback: function(records, options, success){ 
				        			var fieldBoxStore = Ext.getCmp("fieldCode").getStore();
									fieldBoxStore.insert(0,{"fieldName":"新增","fieldCode":""});
		    	   				}
		    	   			});// 刷新子模块下拉框
		        		}
		        	}
				},{
					fieldLabel : '模块CODE',
					readOnly : true,
					id : "modelCodeReadOnly",
					emptyText : "填写模块CODE",
					name : 'modelCode'
				},{
					fieldLabel : '新模块名',
					name : 'newModelName'
				}]
			},{
				layout : "column",
				border : false,
				defaults : {
					labelWidth : 80,
					labelAlign : 'right',
					columnWidth : .33
				},
				defaultType : 'textfield',
				bodyPadding : 3,
				items : [{
					fieldLabel : '字段名',
					xtype : 'combobox',
					displayField : 'fieldName',
					allowBlank : false,
					valueField : 'fieldCode',
					queryMode : 'local',
					value : "",
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'FieldBoxStore',
					name : 'fieldCode',
					id : 'fieldCode',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					},
					listeners: {
		        		select:function(combo){
		        			var fiCode = combo.getValue();
		        			if(fiCode == ""){
		        				Ext.getCmp("fieldCodeReadOnly").setReadOnly(false);
		        			}else{
		        				Ext.getCmp("fieldCodeReadOnly").setReadOnly(true);
		        			}
		        			Ext.getCmp("fieldCodeReadOnly").setValue(fiCode);
		        			var modelCode = Ext.getCmp("modelCode").getValue();
		        			//如果字段和模块中有一个为空，不加载keyValue列表
		        			if(fiCode == "" || modelCode == ""){
		        				return;
		        			}
		        			//加载keyValue列表
		        			Ext.getCmp("keyValueList").getStore().load({
		        				params : {
		    	   					fieldCode : fiCode,
		    	   					modelCode : modelCode
		    	   				}
		        			});
		        		}
		        	}
				},{
					fieldLabel : '字段CODE',
					readOnly : true,
					id : "fieldCodeReadOnly",
					emptyText : "填写字段CODE",
					name : 'fieldCode'
				},{
					fieldLabel : '新字段名',
					name : 'newFieldName'
				}]
			}]
		},{
			id : 'keyValueList',
			autoScroll:true,
			height : 270,
			region: 'center',
			xtype: 'gridpanel',
			forceFit : true,//让每列自动填充满表格
			store : 'KeyValueStore',// 加载数据
			selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
				//mode: 'SINGLE'//单选模式
				mode: 'MULTI'//多选选模式（默认）
			}),
			columns : [{
				header : 'DisplayField',
				dataIndex : 'displayField',
				emptyText : "双击编辑",
				//双击编辑
				field : {
					xtype : 'textfield',
					allowBlank : false
				}
			}, {
				header : 'ValueField',
				dataIndex : 'valueField',
				//双击编辑
				field : {
					xtype : 'textfield',
					allowBlank : false
				}
			}, {
				header : '类型',
				dataIndex : 'notice',
				//双击编辑
				field : {
					xtype : 'textfield',
					allowBlank : false
				}
			}],
			dockedItems : [ {
				xtype : 'pagingtoolbar',// 分页组件
				store : 'KeyValueStore',// 数据
				dock : 'top',// 指定位置top,bottom
				displayInfo : true// 展示信息
			}],// 一个对象或者对象数组, 组件将提供自定义功能
			plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {// 单元格编辑
				clicksToEdit : 2	// 双击进行编辑，默认双击
			}) ]
		}]
	}],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ {
			xtype : 'button',
			text : '添加键值',
			id : "addKeyValue",
			glyph:0xf0fe
		},{
			xtype : 'button',
			text : '删除键值',
			id : "removeKeyValue",
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