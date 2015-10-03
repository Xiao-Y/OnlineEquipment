Ext.define('AM.view.NoticeList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.noticeList',
	layout: 'border',
	border:false,
	items:[{
		region: 'north',
		xtype: 'toolbar',
		border:false,
		items:[{
			xtype : 'button',
			text : '添加',
			glyph : 0xf0fe,
			id : 'addNatice'
		},{
			xtype : 'button',
			id : 'delNotice',
			glyph : 0xf1f8,
			text : '删除'
		}, {
			xtype : 'button',
			text : '修改',
			glyph : 0xf040,
			id : "editNotice"
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryNotice"
		}, {
			xtype : 'button',
			text : '详细信息',
			glyph : 0xf0ae,
			id : "lookNotice"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetNotice"
		} ]
	},{
		id : 'noticeList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		forceFit : true,//让每列自动填充满表格
		store : 'NoticeStore',// 加载数据
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
			header : '公告标题',
			dataIndex : 'noticeTit',
			width : 300
		}, {
			header : '公告内容',
			dataIndex : 'notice',
			width : 500
		}, {
			header : '公告人',
			dataIndex : 'noticeName'
		}, {
			header : '创建时间',
			dataIndex : 'createTime',
			renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}],
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'NoticeStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	}]
});