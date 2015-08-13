//BUG类型
Ext.define('AM.store.BugTypeStore', {
	extend : 'Ext.data.Store',
	fields : ['bugTypeName', 'bugType'],
	data : [{
		bugTypeName : "",
		bugType : "0"
	}, {
		bugTypeName : "规范问题",
		bugType : "1"
	}, {
		bugTypeName : "性能问题",
		bugType : "2"
	}, {
		bugTypeName : "安全问题",
		bugType : "3"
	}, {
		bugTypeName : "页面问题",
		bugType : "4"
	}, {
		bugTypeName : "其它问题",
		bugType : "5"
	},{
		bugTypeName : "功能问题",
		bugType : "6"
	}]
});