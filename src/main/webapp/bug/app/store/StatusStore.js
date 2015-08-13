//BUG状态
Ext.define('AM.store.StatusStore', {
	extend : 'Ext.data.Store',
	fields : ['statusName', 'status'],
	data : [{
		statusName : "",
		status : "0"
	}, {
		statusName : "已修改",
		status : "1"
	},{
		statusName : "未修改",
		status : "2"
	}]
});