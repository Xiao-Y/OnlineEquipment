//BUG严重程度
Ext.define('AM.store.SeverityStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		url : "../bug/getSeverity",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});