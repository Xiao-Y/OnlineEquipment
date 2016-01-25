//BUG优先级
Ext.define('AM.store.PriorityStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		type : "ajax",
		url : "../bug/getPriority",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});
