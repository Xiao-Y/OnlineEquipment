Ext.define('AM.store.ParentMenuStore', {
	extend : 'Ext.data.Store',
	model : 'AM.model.MenuModel',
	proxy : {
		url : '../menu/menuList',
		type : 'ajax',
		extraParams : {
			parentId : "-1"
		},
		reader : {
			type : 'json',
			rootProperty : 'root',
			totalProperty : 'total'
		}
	},
	autoLoad : true
});