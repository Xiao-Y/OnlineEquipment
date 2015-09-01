Ext.define('AM.store.MenuTypeStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		type : "ajax",
		url : "../menu/getMenuType",
		reader : {
			typy : "json",
			rootProperty : "root"
		}
	},
	autoLoad : true
});