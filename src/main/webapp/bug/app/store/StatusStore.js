//BUG状态
Ext.define('AM.store.StatusStore', {
	extend : 'Ext.data.Store',
	fields : ['statusName', 'status'],
	data : [{
		statusName : "未修改",
		status : "0"
	}, {
		statusName : "已修改",
		status : "1"
	}]
});