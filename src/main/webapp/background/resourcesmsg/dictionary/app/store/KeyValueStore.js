//维护数据字典的列表
Ext.define("AM.store.KeyValueStore",{
	extend : "Ext.data.Store",
	model : "AM.model.DictionaryModel",
	proxy : {
		url : "../dictionary/getKeyValue",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	}
});