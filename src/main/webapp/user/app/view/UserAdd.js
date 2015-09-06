Ext.define("AM.view.UserAdd",{
	extend : "Ext.window.Window",
	alias : "widget.userAdd",
	id : "userAddWindow",
	modal : true,
	width : 570,
	height : 360,
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
			fieldLabel : '出生年月',
			xtype : 'datefield',
			format : 'Y-m-d',
			invalidText : "{0}不是一个正确的日期格式，如2015-08-06",
			name : 'birthday'
		}, {
			fieldLabel : '角色',
			name : 'roleName'
		}, {
			fieldLabel : '地址',
			xtype : 'combobox',
			 //hidden : true,// 隐藏
			displayField : 'name',
			allowBlank : false,
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'ZipStore',
			name : 'address',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>",
				maxHeight : 100
			// 最大宽度
			},
			id : 'address'
		}]
	}]
});