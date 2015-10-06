Ext.define("AM.store.AreaStore",{
	extend : "Ext.data.Store",
	model : "AM.model.ZipModel",
	proxy : {
		url : "../zip/getZip",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});