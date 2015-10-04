Ext.define("AM.store.ZipStore",{
	extend : "Ext.data.Store",
	model : "AM.model.ZipModel",
	pageSize : 15,
	proxy : {
		url : "../zip/getZipList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true
});