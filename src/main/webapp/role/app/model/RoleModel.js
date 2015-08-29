Ext.define("AM.model.RoleModel",{
	extend : "Ext.data.Model",
	fields : [{
		name : "id",
		type : "string"
	},{
		name : "roleName",
		type : "string"
	},{
		name : "roleCode",
		type : "string"
	},{
		name : "authorizeStatus",
		type : "string"
	},{
		name : "createTime",
		type : "string"
	},{
		name : "updateTime",
		type : "string"
	}]
});