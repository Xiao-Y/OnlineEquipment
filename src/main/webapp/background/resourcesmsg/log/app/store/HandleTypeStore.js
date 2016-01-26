Ext.define("AM.store.HandleTypeStore",{
	extend : "Ext.data.Store",
	fields : ['valueField', 'displayField'],
	proxy : {
		url : "../log/getHandleType",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});