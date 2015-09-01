//BUG类型
Ext.define('AM.store.BugTypeStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		type : "ajax",
		url:"../bug/getBugType",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});