Ext.define("AM.view.UserList", {
	extend : "Ext.panel.Panel",
	alias : 'widget.userList',
	layout: 'border',
	border:false,
	items:[{
		xtype : 'panel',
		region: 'north',
		items : [{
			region: 'north',
			xtype : 'form',
			id : 'userQueryForm',
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
				items : [{
					xtype : 'hiddenfield',
					name : 'id'
				}, {
					fieldLabel : '用户名',
					name : 'username'
				}, {
					fieldLabel : '出生年月',
					xtype : 'datefield',
					format : 'Y-m-d',
					invalidText : "{0}不是一个正确的日期格式，如2015-08-06",
					name : 'birthday'
				}, {
					fieldLabel : '角色',
					xtype : 'combobox',
					displayField : 'roleName',
					multiSelect : true,//允许多选
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'RoleStore',
					name : 'roleId',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					}
				}, {
					fieldLabel : '省/直辖市',
					xtype : 'combobox',
					displayField : 'name',
					valueField : 'id',
					queryMode : 'local',
					forceSelection : true,// 所选择的值必须是列表中的值
					store : 'ProvinceStore',
					id : 'provinceQ',
					name : 'province',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					},
					listeners: {
		        		select:function(combo){
		        			Ext.getCmp('cityQ').store.removeAll();
		        			Ext.getCmp('areaQ').store.removeAll();
		    	   			Ext.getCmp('cityQ').store.load({
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
					id : 'cityQ',
					name : 'city',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					},
					listeners: {
		        		select:function(combo){
		        			Ext.getCmp('areaQ').store.removeAll();
		    	   			Ext.getCmp('areaQ').store.load({
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
					id : 'areaQ',
					name : 'area',
					listConfig : {// 下拉列表的样式
						emptyText : "<font color='red'>没有找到匹配项</font>"
					}
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
				}]
			}]
		},{
			region : "center",
			xtype: 'toolbar',
			border:false,
			items : [{
				xtype : 'button',
				text : '添加',
				glyph : 0xf0fe,
				id : 'addUser'
			}, {
				xtype : 'button',
				id : 'delUser',
				glyph : 0xf1f8,
				text : '删除'
			}, {
				xtype : 'button',
				text : '修改',
				glyph : 0xf040,
				id : "editUser"
			},{
				xtype : 'button',
				text : '查询',
				id : 'queryUser',
				glyph : 0xf002
			}, {
				xtype : 'button',
				text : '重置',
				glyph : 0xf021,
				id : "listResetUser"
			} ]
		}]
	},{
		id : 'userList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		store : 'UserStore',// 加载数据
		forceFit : true,//让每列自动填充满表格
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		columns : [ 
		 {
			xtype : 'rownumberer',
			header : '序号',
			width : 50
		}, {
			header : 'ID',
			hidden : true,
			dataIndex : 'id'
		}, {
			header : '用户名',
			dataIndex : 'username'
		}, {
			header : '出生年月',
			dataIndex : 'birthday'
		}, {
			header : '地址',
			dataIndex : 'address',
			width : 240
		}, {
			hidden : true,
			dataIndex : 'province'
		}, {
			hidden : true,
			dataIndex : 'city'
		}, {
			hidden : true,
			dataIndex : 'area'
		}, {
			header : '角色名称',
			dataIndex : 'roleName'
		}, {
			header : '创建时间',
			dataIndex : 'createTime'
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}],
		dockedItems : [{
			xtype : "pagingtoolbar",
			store : "UserStore",
			dock : "bottom",
			displayInfo : true
		}]
	}]
});