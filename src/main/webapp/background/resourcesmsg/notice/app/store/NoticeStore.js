Ext.define("AM.store.NoticeStore",{
	extend : "Ext.data.Store",
	model : "AM.model.NoticeModel",
	pageSize : 15,
	proxy : {
		url : "../notice/getNoticeList",
		type : "ajax",
		reader : {
			type : "json",
			rootProperty : "root",
			totalProperty : "total"
		}
	},
	autoLoad : true
});