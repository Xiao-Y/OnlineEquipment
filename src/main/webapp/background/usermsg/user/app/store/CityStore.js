Ext.define("AM.store.CityStore",{
	extend : "Ext.data.Store",
	model : "AM.model.ZipModel",
	proxy : {
		url : "../user/getZip",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});