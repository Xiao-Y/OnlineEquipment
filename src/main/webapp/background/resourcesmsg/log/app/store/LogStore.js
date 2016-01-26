Ext.define("AM.store.LogStore",{
	extend : "Ext.data.Store",
	model : "AM.model.LogModel",
	pageSize : 15,
	proxy : {
		url : "../log/getLogList",
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
	        var logQueryForm = Ext.getCmp("logQueryForm")
	        if(logQueryForm){
		        var fv = logQueryForm.getValues(); 
		        Ext.apply(store.proxy.extraParams, fv);    
	        }
	    }
	}
});