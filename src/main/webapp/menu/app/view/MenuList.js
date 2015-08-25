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
		forceFit : true,//让每列自动填充满表格
		stripeRows:true,//斑马线效果,默认为true
		selModel: new Ext.selection.CheckboxModel({// 复选框选择模式
			mode: 'SINGLE'//单选模式
		}),
		
		columns : [ 
		 {
			xtype : 'rownumberer',
			width : 50,
			header : '序号'
		}, 
		{
			header : 'ID',
			hidden : true,
			dataIndex : 'id'
		}, {
			header : '菜单名称',
			dataIndex : 'menuName'
		}, {
			header : '请求路径',
			dataIndex : 'menuUrl'
		}, {
			header : '所在位置',
			dataIndex : 'seq'
		}, {
			header : '父级ID',
			dataIndex : 'parentId'
		}, {
			header : '父级菜单名称',
			dataIndex : 'parentName'
		}, {
			header : '节点类型',
			width : 80,
			dataIndex : 'menuType',
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
			dataIndex : 'createTime'
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}, {
			header : '备注',
			dataIndex : 'remark'
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