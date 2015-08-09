var msgTip;
Ext.define('AM.store.MenuTreeStore', {
	extend : 'Ext.data.TreeStore',
	model : 'AM.model.MenuTreeModel',
	defaultRoodId : 'root',
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
