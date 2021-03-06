Ext.define("AM.model.BugModel",{
	extend:"Ext.data.Model",
	fields:[{
		name:"id",
		type:"string"
	},{
		name:"title",
		type:"string"
	},{
		name:"note",
		type:"string"
	},{
		name:"createTime",
		type:"string",
		sortable : true
	},{
		name:"updateTime",
		type:"string",
		sortable : true
	},{
		name:"imgUrl",
		type:"string"
	},{
		name:"status",
		type:"string"
	},{
		name:"reviseExplain",
		type:"string"
	},{
		name:"parentId",
		type:"string"
	},{
		name:"parentName",
		type:"string"
	},{
		name:"childrenId",
		type:"string"
	},{
		name:"childrenName",
		type:"string"
	},{
		name:"severity",
		type:"string"
	},{
		name:"reappear",
		type:"string"
	},{
		name:"bugType",
		type:"string"
	},{
		name:"priority",
		type:"string"
	}]
});