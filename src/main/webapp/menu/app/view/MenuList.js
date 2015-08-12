Ext.define('AM.view.MenuList', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.menuList',
	layout: 'border',
	items:[{
		region: 'north',
		xtype: 'toolbar',
		items:[{
			xtype : 'button',
			text : '添加',
			glyph : 0xf0fe,
			id : 'addMenu'
		}, {
			xtype : 'button',
			id : 'delMenu',
			glyph : 0xf146,
			text : '删除'
		}, {
			xtype : 'button',
			text : '修改',
			glyph : 0xf0c7,
			id : "editMenu"
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryMenu"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetMenu"
		} ]
	},{
		id : 'menuList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		store : 'MenuStore',// 加载数据
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		columns : [ 
		 {
			xtype : 'rownumberer',
			header : '序号',
			width : 40
		}, 
		{
			header : 'ID',
			hidden : true,
			dataIndex : 'id',
			width : 120
		}, {
			header : '菜单名称',
			dataIndex : 'menuName',
			//locked : true,	//锁定
			width : 130
		}, {
			header : '请求路径',
			dataIndex : 'menuUrl',
			width : 150
		}, {
			header : '所在位置',
			dataIndex : 'seq',
			width : 80
		}, {
			header : '父级ID',
			dataIndex : 'parentId',
//			hidden : true,
			width : 120
		}, {
			header : '父级菜单名称',
			dataIndex : 'parentName',
			width : 120
		}, {
			header : '节点类型',
			dataIndex : 'menuType',
			width : 80,
			renderer : function(value) {
				if (value == '0') {
					return '树枝节点';
				}
				if (value == '1') {
					return '叶子节点';
				}
			}
		}, {
			header : '创建时间',
			dataIndex : 'createTime',
			width : 140
		}, {
			header : '更新时间',
			dataIndex : 'updateTime',
			width : 140
		}, {
			header : '备注',
			dataIndex : 'remark',
			width : 160
		} ],
		// 一个或者一系列组件作为挂靠组件被添加到panel中
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'MenuStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	},{
		title: '查询统计信息',
		region: 'south',
		xtype: 'panel',
		split:true,
		height:150,
		layout:'fit',
		items: [{
            xtype: 'textareafield',
            id: 'log',
            readOnly: true
        }] 
	}]
});