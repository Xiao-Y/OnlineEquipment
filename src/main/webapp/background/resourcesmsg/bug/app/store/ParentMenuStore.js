//查询出所有的父集合
Ext.define('AM.store.ParentMenuStore', {
	extend : 'Ext.data.Store',
	model : 'AM.model.MenuModel',
	proxy : {
		url : '../bug/parentMenuList',
		type : 'ajax',
		extraParams : {
			parentId : "-1"
		},
		reader : {
			type : 'json',
			rootProperty : 'root'
		}
	}
});