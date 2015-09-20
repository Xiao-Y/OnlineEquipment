Ext.define('AM.model.DictionaryModel',{
	extend : "Ext.data.Model",
	fields : [{
		name : "id",
		type : "string"
	},{
		name : "modelName",
		type : "string"
	},{
		name : "fieldName",
		type : "string"
	},{
		name : "displayField",
		type : "string"
	},{
		name : "valueField",
		type : "string"
	},{
		name : "notice",
		type : "string"
	},{
		name : "createTime",
		type : "string"
	},{
		name : "updateTime",
		type : "string"
	}]
});