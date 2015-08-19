Ext.define("AM.store.HandleTypeStore",{
	extend : "Ext.data.Store",
	fields : ['id', 'operation'],
	data : [{
		id : "delete",
		operation : "删除操作"
	},{
		id : "save",
		operation : "保存操作"
	},{
		id : "update",
		operation : "更新操作"
	}],
	autoLoad : true
});