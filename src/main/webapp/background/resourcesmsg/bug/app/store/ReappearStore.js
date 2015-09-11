//重现规律
Ext.define('AM.store.ReappearStore', {
	extend : 'Ext.data.Store',
	fields : ['displayField', 'valueField'],
	proxy : {
		url : "../bug/getReappear",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root"
		}
	}
//	data : [{
//		reappearName : "",
//		reappear : "0"
//	}, {
//		reappearName : "很难重现",
//		reappear : "1"
//	}, {
//		reappearName : "随机重现",
//		reappear : "2"
//	},{
//		reappearName : "必然重现",
//		reappear : "3"
//	}]
});