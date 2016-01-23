Ext.define("AM.store.ZipStore",{
	extend : "Ext.data.Store",
	model : "AM.model.ZipModel",
	pageSize : 15,
	proxy : {
		url : "../zip/getZipList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true,
	listeners:{//用于翻页查询
	    beforeload : function (store, options) {    
	        var fv = Ext.getCmp("zipQueryForm").getValues(); 
	        Ext.apply(store.proxy.extraParams, fv);    
	    }
	}
});