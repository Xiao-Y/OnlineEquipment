Ext.define("AM.model.UserModel", {
	extend : "Ext.data.Model",
	fields : [{
		name : "id",
		type : "string"
	},{
		name : "username",
		type : "string",
		sortable : true
	},{
		name : "password",
		type : "string"
	},{
		name : "birthday",
		type : "string",
		sortable : true
	},{
		name : "createTime",
		type : "string",
		sortable : true
	},{
		name : "updateTime",
		type : "string",
		sortable : true
	},{
		name : "imageUrl",
		type : "string"
	},{
		name : "address",
		type : "string"
	},{
		name : "roleName",
		type : "string"
	}]
});