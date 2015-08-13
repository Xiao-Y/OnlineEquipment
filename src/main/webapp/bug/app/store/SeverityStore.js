//BUG严重程度
Ext.define('AM.store.SeverityStore', {
	extend : 'Ext.data.Store',
	fields : ['severityName', 'severity'],
	data : [{
		severityName : "",
		severity : "0"
	}, {
		severityName : "提示",
		severity : "1"
	}, {
		severityName : "一般",
		severity : "2"
	}, {
		severityName : "严重",
		severity : "3"
	}, {
		severityName : "致命",
		severity : "4"
	},{
		severityName : "建议",
		severity : "5"
	}]
});