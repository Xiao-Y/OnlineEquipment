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
		}]
	}]
});