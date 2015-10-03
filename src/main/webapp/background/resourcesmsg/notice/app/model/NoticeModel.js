Ext.define("AM.model.NoticeModel",{
	extend : "Ext.data.Model",
	fields : [{
		name : "id",
		type : "string"
	},{
		name : "noticeTit",
		type : "string"
	},{
		name : "createTime",
		type : "date",
		sortable : true
	},{
		name : "updateTime",
		type : "string",
		sortable : true
	},{
		name : "notice",
		type : "string"
	},{
		name : "noticeName",
		type : "string"
	}]
});