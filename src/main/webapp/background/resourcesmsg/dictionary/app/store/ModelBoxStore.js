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
	listeners:{
	    load : function( store, records, successful, operation){
		    if(successful){
		        store.insert(0,{"modelName":"新增","modelCode":""});
		    }
		}
	}
});