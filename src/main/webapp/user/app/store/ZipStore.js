Ext.define("AM.store.ZipStore",{
	extend : "Ext.data.Store",
	model : "AM.model.ZipModel",
	proxy : {
		url : "../user/getProvince",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});