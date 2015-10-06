Ext.define('AM.view.ZipAdd', {
	extend : 'Ext.window.Window',
	alias : "widget.zipAdd",
	id : 'zipAddWindow',
	height : 180,
	width : 450,
	layout : 'fit',
	modal:true,//模态框
	title:"导入Excel",
	items : [ {
		xtype : 'form',
		id : 'zipAddForm',
		buttonAlign : 'right',
		bodyPadding : 15,
		autoScroll : true,
		layout : 'anchor',
		border:false,
		defaults : {
			labelAlign : 'right',
			labelWidth : 80,
			anchor : '90%'
		},
		items : [{
			xtype : 'filefield',
			name: 'multipartFile',
			fieldLabel: '导入Excel',
			allowBlank : false,
			blankText : "请选择导入的Excel文件",
    		buttonText: '上传'
		},{
			xtype: 'displayfield',
			fieldLabel: '提示',
			value: "<font color='red'>导入新数据时，会删除所有的旧数据！</font>"
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '导入',
			id : 'saveZip',
			glyph : 0xf002
		}, {
			xtype : 'button',
			id : 'cancel',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});