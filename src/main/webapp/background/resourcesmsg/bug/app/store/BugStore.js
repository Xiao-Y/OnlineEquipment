Ext.define("AM.store.BugStore",{
	extend:"Ext.data.Store",
	model : 'AM.model.BugModel',
	pageSize : 15,
	proxy:{
		type:"ajax",
		url:"../bug/getBugList",
		reader:{
			type:"json",
			rootProperty:"root",
			totalProperty:"total"
		}
	},
	autoLoad:true,
	listeners:{//用于翻页查询
	    beforeload : function (store, options) {    
	    	var bugQueryForm = Ext.getCmp("bugQueryForm");
	    	if(bugQueryForm){
		        var fv = bugQueryForm.getValues(); 
		        //store.proxy.extraParams = fv;
		        Ext.apply(store.proxy.extraParams, fv);    
	    	}
	    }
	}
});