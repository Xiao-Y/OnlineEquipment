Ext.define("AM.store.RoleStore",{
	extend : "Ext.data.Store",
	model : "AM.model.RoleModel",
	pageSize : 15,
	proxy : {
		url : "../role/getRoleList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true
});