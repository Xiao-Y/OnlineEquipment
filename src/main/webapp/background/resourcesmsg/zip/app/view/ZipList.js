Ext.define('AM.view.ZipList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.zipList',
	layout: 'border',
	border:false,
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
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryZip"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetZip"
		} ]
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
			header : '全称',
			dataIndex : 'name'
		}, {
			header : '上级行政码',
			dataIndex : 'parentId'
		}, {
			header : '简称',
			dataIndex : 'shortName'
		}, {
			header : '等级',
			dataIndex : 'levelType'
		}, {
			header : '城市Code',
			dataIndex : 'cityCode'
		}, {
			header : '邮编',
			dataIndex : 'zipCode'
		}, {
			header : '完整名称',
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