Ext.define("AM.store.UserStore",{
	extend : "Ext.data.Store",
	model : "AM.model.UserModel",
	pageSize : 15,
	proxy : {
		url : "../background/usermsg/user/getUserList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true
});