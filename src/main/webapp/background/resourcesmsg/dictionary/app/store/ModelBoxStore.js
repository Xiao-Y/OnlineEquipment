Ext.define("AM.store.ModelBoxStore",{
	extend : "Ext.data.Store",
	model : "AM.model.DictionaryModel",
	proxy : {
		url : "../dictionary/getModelNameCheckBox",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	},
	autoLoad : true
});