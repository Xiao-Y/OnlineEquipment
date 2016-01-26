Ext.define("AM.store.ProvinceStore",{
	extend : "Ext.data.Store",
	model : "AM.model.ZipModel",
	proxy : {
		url : "../user/getZip",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	},
	autoLoad : true
});