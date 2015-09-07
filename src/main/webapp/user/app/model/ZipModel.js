Ext.define("AM.model.ZipModel", {
	extend : "Ext.data.Model",
	fields : [{
		name : "id",
		type : "string"
	},{
		name : "name",
		type : "string"
	},{
		name : "parentId",
		type : "string"
	},{
		name : "shortName",
		type : "string"
	},{
		name : "levelType",
		type : "string"
	},{
		name : "cityCode",
		type : "string"
	},{
		name : "zipCode",
		type : "string"
	},{
		name : "mergerName",
		type : "string"
	},{
		name : "lng",
		type : "string"
	},{
		name : "lat",
		type : "string"
	},{
		name : "pinyin",
		type : "string"
	},{
		name : "province",
		type : "string"
	},{
		name : "city",
		type : "string"
	},{
		name : "area",
		type : "string"
	}]
});