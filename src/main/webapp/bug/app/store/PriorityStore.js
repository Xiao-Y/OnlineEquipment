//BUG优先级
Ext.define('AM.store.PriorityStore', {
	extend : 'Ext.data.Store',
	fields : ['priorityName', 'priority'],
	data : [{
		priorityName : "",
		priority : "0"
	}, {
		priorityName : "低",
		priority : "1"
	}, {
		priorityName : "中",
		priority : "2"
	}, {
		priorityName : "高",
		priority : "3"
	}, {
		priorityName : "紧急",
		priority : "4"
	},{
		priorityName : "无关紧要",
		priority : "5"
	}]
});
