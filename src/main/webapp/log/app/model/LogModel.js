Ext.define("AM.model.LogModel",{
	extend : "Ext.data.Model",
	fields : [{
		name : "id",
		type : "string"
	},{
		name : "userId",
		type : "string"
	},{
		name : "createTime",
		type : "string",
		sortable : true
	},{
		name : "runClass",
		type : "string"
	},{
		name : "content",
		type : "string"
	},{
		name : "operation",
		type : "string"
	}]
});