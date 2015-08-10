Ext.define('AM.store.MenuTypeStore', {
	extend : 'Ext.data.Store',
	fields : ['menuTypeName', 'menuType'],
	data : [{
				"menuTypeName" : "树枝节点",
				"menuType" : "0"
			}, {
				"menuTypeName" : "叶子节点",
				"menuType" : "1"
			}],
	autoLoad : true
});