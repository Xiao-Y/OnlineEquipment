Ext.define('AM.model.MenuModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'menuName',
		type : 'string',
		sortable : true
	}, {
		name : 'menuCode',
		type : 'string',
		sortable : true
	}, {
		name : 'menuUrl',
		type : 'string'
	}, {
		name : 'parentId',
		type : 'string',
		sortable : true
	}, {
		name : 'parentName',
		type : 'string',
		sortable : true
	}, {
		name : 'menuType',
		type : 'string'
	}, {
		name : 'seq',
		type : 'int',
		sortable : true
	}, {
		name : 'remark',
		type : 'string'
	}, {
		name : 'createTime',
		type : 'string',
		sortable : true
	}, {
		name : 'updateTime',
		type : 'string',
		sortable : true
	} ]
});