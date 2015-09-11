Ext.define('AM.model.MenuTreeModel', {
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
	},
	// type为布尔型时，后面的默认值是必须写的，要不会出错
	{
		name : 'leaf',
		type : 'boolean',
		defaultValue : true
	}, {
		name : 'url',
		type : 'string'
	}, {
		name : 'index',
		type : 'int'
	} ]
});
