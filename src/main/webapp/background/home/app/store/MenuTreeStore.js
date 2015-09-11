var msgTip;
Ext.define('AM.store.MenuTreeStore', {
	extend : 'Ext.data.TreeStore',
	model : 'AM.model.MenuTreeModel',
	//defaultRoodId : 'root',
	// 设置根节点
	root : {
	  text : '根节点',
	  id : '-1',
	  leaf : false,//是否为叶子
	  expanded : true//是否展开
	},
	proxy : {
		url : '../home/buildTree',
		type : 'ajax',
		reader : {
			type : 'json'
		},
		writer : {
			type : 'json'
		}
	},
	listeners : {
		beforeload : function() {
			msgTip = Ext.Msg.show({
				title : '提示',
				width : 250,
				msg : '正在进入系统。。。'
			});
		},
		load : function() {
			msgTip.hide(); // 加载完成，关闭提示框
		}
	},
	autoLoad : true
});
