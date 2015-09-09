Ext.define("AM.store.RoleStore",{
	extend : "Ext.data.Store",
	model : "AM.model.RoleModel",
	proxy : {
		url : "../user/getRoleList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
});