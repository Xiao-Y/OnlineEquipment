Ext.define("AM.store.FieldBoxStore",{
	extend : "Ext.data.Store",
	model : "AM.model.DictionaryModel",
	proxy : {
		url : "../dictionary/getFieldNameCheckBox",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});