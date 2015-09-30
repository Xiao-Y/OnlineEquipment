Ext.define("AM.store.DictionaryStore",{
	extend : "Ext.data.Store",
	model : "AM.model.DictionaryModel",
	pageSize : 15,
	proxy : {
		url : "../dictionary/getDictionary",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true
});