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
	},
	listeners:{
	    load : function(store, records, successful, operation){
		    if(successful){
		        store.insert(0,{"fieldName":"新增","fieldCode":""});
		    }
		}
	}
});