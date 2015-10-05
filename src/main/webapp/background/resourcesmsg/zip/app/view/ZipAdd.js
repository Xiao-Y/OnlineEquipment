Ext.define('AM.view.ZipAdd', {
	extend : 'Ext.window.Window',
	alias : "widget.zipAdd",
	id : 'zipAddWindow',
	height : 150,
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
		items : [{
			xtype : 'filefield',
			labelWidth : 80,
			labelAlign : 'right',
			name: 'multipartFile',
			fieldLabel: '导入Excel',
			allowBlank : false,
			blankText : "请选择导入的Excel文件",
    		buttonText: '上传',
			anchor : '90%'
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