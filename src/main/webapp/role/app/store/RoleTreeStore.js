Ext.define('AM.store.RoleTreeStore', {
	extend : 'Ext.data.TreeStore',
	//model : 'AM.model.RoleTreeModel',
	defaultRoodId : 'root',
	// 设置根节点
	root : {
	  text : '根节点',
	  id : '-1',
	  leaf : false,//是否为叶子
	  expanded : true//是否展开
	},
	proxy : {
		url : '../role/buildTree',
		type : 'ajax',
		reader : {
			type : 'json',
			defaultRootProperty : "root"
		}
	},
	autoLoad : true
});
