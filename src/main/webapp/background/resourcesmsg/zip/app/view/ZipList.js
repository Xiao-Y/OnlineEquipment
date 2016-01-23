Ext.define('AM.view.ZipList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.zipList',
	layout: 'border',
	border:false,
	items:[{
		xtype : 'panel',
		region: 'north',
		items:[{
			region: 'north',
			xtype: 'toolbar',
			border:false,
			items:[{
				xtype : 'button',
				text : '导入地区',
				glyph : 0xf0fe,
				id : 'importZip'
			},{
				xtype : 'button',
				id : 'exportZip',
				glyph : 0xf1f8,
				text : '导出地区'
			},{
				xtype : 'button',
				text : '查询',
				id : 'queryZip',
				glyph : 0xf002
			}, {
				xtype : 'button',
				text : '重置',
				id : 'listResetZip',
				glyph : 0xf021
			}]
		},{
			region: 'center',
			xtype : 'form',
			id : 'zipQueryForm',
			items : [{
				xtype : "fieldset",
				title : "查询条件",
				collapsible : true,// 显示折叠按钮形式
				collapsed : false,// 设置为 true 则将 fieldset 初始化为收缩状态。默认为false
				layout : {
					type: 'table',
       				columns: 4 //每行有几列
				},
				defaults : {
					labelWidth : 90,
					labelAlign : 'right'
				},
				defaultType : 'textfield',
				items : [ {
					fieldLabel : '省/直辖市',
					xtype : 'combobox',
					displayField : 'name',
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'ProvinceStore',
					id : 'province',
					name : 'province',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					},
					listeners: {
		        		select:function(combo){
		        			Ext.getCmp('area').getStore().removeAll();
		        			Ext.getCmp('area').setValue("");
		        			Ext.getCmp('city').getStore().removeAll();
		    	   			Ext.getCmp('city').getStore().load({
		    	   				params : {
		    	   					province:combo.getValue()
		    	   				}
		    	   			});// 刷新下拉框
		        		}
		        	}
				}, {
					fieldLabel : '市/直辖县级',
					xtype : 'combobox',
					displayField : 'name',
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'CityStore',
					id : 'city',
					name : 'city',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					},
					listeners: {
		        		select:function(combo){
		        			Ext.getCmp('area').getStore().removeAll();
		    	   			Ext.getCmp('area').getStore().load({
		    	   				params : {
		    	   					city:combo.getValue()
		    	   				}
		    	   			});// 刷新子模块下拉框
		        		}
		        	}
				}, {
					fieldLabel : '区/县',
					xtype : 'combobox',
					displayField : 'name',
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'AreaStore',
					id : 'area',
					name : 'area',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					}
				},{
					fieldLabel : '行政码',
					name : 'id'
				},{
					fieldLabel : '地区名称',
					name : 'name'
				},{
					fieldLabel : '上级行政码',
					name : 'parentId'
				},{
					fieldLabel : '地区等级',
					name : 'levelType'
				},{
					fieldLabel : '城市CODE',
					name : 'cityCode'
				},{
					fieldLabel : '邮编',
					name : 'zipCode'
				}]
			}]
		}]
	},{
		id : 'zipList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		forceFit : true,//让每列自动填充满表格
		store : 'ZipStore',// 加载数据
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		columns : [{
			header : '行政码',
			dataIndex : 'id'
		}, {
			header : '地区全称',
			dataIndex : 'name'
		}, {
			header : '上级行政码',
			dataIndex : 'parentId'
		}, {
			header : '地区简称',
			dataIndex : 'shortName'
		}, {
			header : '地区等级',
			dataIndex : 'levelType'
		}, {
			header : '城市CODE',
			dataIndex : 'cityCode'
		}, {
			header : '邮编',
			dataIndex : 'zipCode'
		}, {
			header : '地区完整名称',
			dataIndex : 'mergerName',
			width : 200
		}, {
			header : '经度',
			dataIndex : 'lng'
		}, {
			header : '纬度',
			dataIndex : 'lat'
		}, {
			header : '名称拼音',
			dataIndex : 'pinyin'
		}],
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'ZipStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	}]
});