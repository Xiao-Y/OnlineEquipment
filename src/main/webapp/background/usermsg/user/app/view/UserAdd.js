Ext.define("AM.view.UserAdd",{
	extend : "Ext.window.Window",
	alias : "widget.userAdd",
	id : "userAddWindow",
	modal : true,
	width : 570,
	height : 300,
	layout : "fit",
	items : [{
		id : "userAddForm",
		xtype : 'form',
		buttonAlign : 'right',
		bodyPadding : 15,
		autoScroll : true,
		layout : 'anchor',
		defaults : {
			labelWidth : 130,
			labelAlign : 'right',
			anchor : '90%'
		},
		defaultType : 'textfield',
		items : [{
			xtype : 'hiddenfield',
			name : 'id'
		}, {
			fieldLabel : '用户名',
			name : 'username',
			allowBlank : false
		}, {
			fieldLabel : '密码',
			inputType : 'password',// radio,text,password,file
			emptyText : '密码输入',// 当为空时显示的内容
			value : "111111",
			name : 'password',
			allowBlank : false
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
			allowBlank : false,
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'RoleStore',
			name : 'roleId',
			id : 'roleId',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100// 最大宽度
			}
		}, {
			fieldLabel : '省市/直辖市',
			xtype : 'combobox',
			displayField : 'name',
			allowBlank : false,
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'ProvinceStore',
			id : 'province',
			name : 'province',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100// 最大宽度
			},
			listeners: {
        		select:function(combo){
        			Ext.getCmp('city').store.removeAll();
    	   			Ext.getCmp('city').store.load({
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
			allowBlank : false,
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'CityStore',
			id : 'city',
			name : 'city',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100// 最大宽度
			},
			listeners: {
        		select:function(combo){
        			Ext.getCmp('area').store.removeAll();
    	   			Ext.getCmp('area').store.load({
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
			allowBlank : false,
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'AreaStore',
			id : 'area',
			name : 'area',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100// 最大宽度
			}
		}]
	}],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '保存',
			id : 'saveUser',
			glyph:0xf0c7
		}, {
			xtype : 'button',
			text : '重置',
			id : 'resetUser',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancelUser',
			text : '关闭',
			glyph : 0xf00d
		} ]
	} ]
});