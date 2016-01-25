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
	autoLoad : true,
	listeners:{//用于翻页查询
	    beforeload : function (store, options) {    
	    	var noticeQueryForm = Ext.getCmp("noticeQueryForm");
	    	if(noticeQueryForm){
		        var fv = noticeQueryForm.getValues(); 
		        Ext.apply(store.proxy.extraParams, fv);    
	    	}
	    }
	}
});