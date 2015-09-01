//BUG优先级
Ext.define('AM.store.PriorityStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		type : "ajax",
		reader : {
			type : "json",
			url : "../bug/getPriority",
			rootProperty : "root"
		}
	}
});
