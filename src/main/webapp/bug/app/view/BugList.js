Ext.define("AM.view.BugList",{
	extend : 'Ext.panel.Panel',
	alias : 'widget.bugList',
	layout: 'border',
	items:[{
		region: 'north',
		xtype: 'toolbar',
		items:[{
			xtype : 'button',
			text : '添加',
			glyph : 0xf0fe,
			id : 'addBug'
		}, {
			xtype : 'button',
			id : 'delBug',
			glyph : 0xf146,
			text : '删除'
		}, {
			xtype : 'button',
			text : '修改',
			glyph : 0xf0c7,
			id : "editBug"
		}, {
			xtype : 'button',
			text : '高级查询',
			glyph : 0xf002,
			id : "topQueryBug"
		}, {
			xtype : 'button',
			text : '重置',
			glyph : 0xf021,
			id : "listResetBug"
		} ]
	},{
		id : 'bugList',
		region: 'center',
		xtype: 'gridpanel',
		autoScroll:true,
		forceFit : true,//让每列自动填充满表格
		store : 'BugStore',// 加载数据
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
			dataIndex : 'id',
			width : 120
		}, {
			header : 'BUG标题',
			dataIndex : 'title',
			width : 300
		}, {
			header : 'BUG状态',
			dataIndex : 'status',
			width : 80,
			renderer:function(value){
				if(value=='2'){return "<div style=' background:red;'>" + '未修改' + "</div>";}
				if(value=='1'){return "<div style=' background:green;'>" + '已修改' + "</div>";}
        	}
		}, {
			header : '父模块',
			dataIndex : 'parentName'
		}, {
			header : '子模块',
			dataIndex : 'childrenName'
		}, {
			header : '严重程度',
			dataIndex : 'severity',
			width : 80,
			renderer:function(value){
				if(value=='5'){return "<font color='3CCF26'>" + '建议' + "</font>";}
				if(value=='1'){return "<font color='32C39F'>" + '提示' + "</font>";}
				if(value=='2'){return "<font color='D55920'>" + '一般' + "</font>";}
				if(value=='3'){return "<font color='BFBF08'>" + '严重' + "</font>";}
				if(value=='4'){return "<font color='FA0007'>" + '致命' + "</font>";}
        	}
		}, {
			header : '重现规律',
			dataIndex : 'reappear',
			width : 80,
			renderer:function(value){
				if(value=='3'){return '必然重现';}
				if(value=='1'){return '很难重现';}
				if(value=='2'){return '随机重现';}
        	}
		}, {
			header : 'BUG类型',
			dataIndex : 'bugType',
			renderer:function(value){
				if(value=='6'){return '功能问题';}
				if(value=='1'){return '规范问题';}
				if(value=='2'){return '性能问题';}
				if(value=='3'){return '安全问题';}
				if(value=='4'){return '页面问题';}
				if(value=='5'){return '其它问题';}
        	}
		}
//		, {
//			header : '优先级',
//			dataIndex : 'priority',
//			width : 80
//		}
		, {
			header : '创建时间',
			dataIndex : 'createTime'
		}, {
			header : '更新时间',
			dataIndex : 'updateTime'
		}],
		// 一个或者一系列组件作为挂靠组件被添加到panel中
		dockedItems : [ {
			xtype : 'pagingtoolbar',// 分页组件
			store : 'BugStore',// 数据
			dock : 'bottom',// 指定位置top,bottom
			// 展示信息
			displayInfo : true
		}]
	},{
		title: 'BUG截图信息',
		region: 'south',
		xtype: 'panel',
		id:'bugImagePanel',
		autoScroll:true,
		split:true,
		height:150,
//		layout: 'fit'
		layout: 'column'
	}]
});