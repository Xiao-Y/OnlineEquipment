//BUG状态
Ext.define('AM.store.StatusStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		type : "ajax",
		url : "../bug/getStatus",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});