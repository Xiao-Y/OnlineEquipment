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
	autoLoad:true
});