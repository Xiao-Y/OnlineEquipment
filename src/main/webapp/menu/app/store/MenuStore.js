Ext.define('AM.store.MenuStore', {
	extend : 'Ext.data.Store',
	model : 'AM.model.MenuModel',
	pageSize : 15,
	proxy : {
		url : '../menu/menuList',
		type : 'ajax',
		reader : {
			type : 'json',
			rootProperty : 'root',
			totalProperty : 'total'
		},
		writer : {
			type : 'json'
		}
	},
	autoLoad : true
});