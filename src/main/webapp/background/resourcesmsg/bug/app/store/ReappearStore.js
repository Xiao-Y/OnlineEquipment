//重现规律
Ext.define('AM.store.ReappearStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		url : "../bug/getReappear",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});