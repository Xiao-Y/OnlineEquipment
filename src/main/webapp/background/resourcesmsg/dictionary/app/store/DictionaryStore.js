Ext.define("AM.store.DictionaryStore",{
	extend : "Ext.data.Store",
	model : "AM.model.DictionaryModel",
	pageSize : 12,
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