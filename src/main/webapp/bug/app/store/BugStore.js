Ext.define("AM.store.BugStoer",{
	extend:"Ext.data.Store",
	model : 'AM.model.BugModel',
	pageSize : 15,
	proxy:{
		tyep:"ajax",
		url:"../bug/getBugList",
		reader:{
			type:"json",
			rootProperty:"root",
			totalProperty:"total"
		}
	},
	autoLoad:true
});