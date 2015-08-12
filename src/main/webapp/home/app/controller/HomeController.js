Ext.define('AM.controller.HomeController', {
	extend : 'Ext.app.Controller',
	// 将Viewport.js添加到控制器
	views : ['Viewport', 'MenuTreeView', 'ContextMenu'],
	stores : ['MenuTreeStore'],
	models : ['MenuTreeModel'],//写成了model,导致后面的url没有获取 
	// 通过init函数来监听视图事件，控制视图与控制器的交互
	init : function() {
		// init函数通过this.control来负责监听
		this.control({
			// 被监听的组件的别名
			'menutree' : {
				// 监听鼠标点击事件，点击后调用changePage方法
				itemclick : this.changePage
			}
		});
	},
	changePage : function(view, rec, item, index, e) {
		// 获取url地址
		var url = rec.get('url');
		if(''==url||null==url||'../'==url){
			return false;
		}
		// 获取当前节点信息
		var title = rec.get('text');
		var id = rec.get('id');
		var mainPanel = Ext.getCmp('mainContent');
		if (!mainPanel.child('#' + id)) {
			var tab = Ext.create('AM.view.ContentPanel', {
				itemId : id,
				url : url,
				title : title,
				closable : true,
				glyph:0xf123
			});
			mainPanel.add(tab).show().doLayout();
		} else {
			mainPanel.setActiveTab(id);
		}
	}
});