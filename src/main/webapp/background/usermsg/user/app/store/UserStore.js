Ext.define("AM.store.UserStore",{
	extend : "Ext.data.Store",
	model : "AM.model.UserModel",
	pageSize : 15,
	proxy : {
		url : "../user/getUserList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true,
	listeners : {//用于翻页查询
	    beforeload : function (store, options) {    
	    	var userQueryForm = Ext.getCmp("userQueryForm");
	    	if(userQueryForm){
		        var fv = userQueryForm.getValues(); 
		        Ext.apply(store.proxy.extraParams, fv);    
	    	}
	    }
	}
});