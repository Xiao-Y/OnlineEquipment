Ext.define("AM.store.LogStore",{
	extend : "Ext.data.Store",
	model : "AM.model.LogModel",
	pageSize : 15,
	proxy : {
		url : "../log/getLogList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true
});