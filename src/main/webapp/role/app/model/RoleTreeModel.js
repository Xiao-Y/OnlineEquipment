Ext.define('AM.model.RoleTreeModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'parentId',
		type : 'string'
	}, {
		name : 'text',
		type : 'string'
	},{
		name : 'leaf',
		type : 'boolean'
	}, {
		name : 'url',
		type : 'string'
	}, {
		name : 'index',
		type : 'int'
	} ]
});
