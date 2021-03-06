Ext.define('AM.controller.LogController', {
	extend : 'Ext.app.Controller',
	views : [ 'LogList',"LogView"],
	stores : [ 'LogStore',"HandleTypeStore"],
	models : [ 'LogModel' ],
	init : function() {
		this.control({
			"logList button[id=topQueryLog]" : {//打开查询窗口
				click : this.topQueryLog
			},
			"logList button[id=listResetLog]" : {
				click : this.listResetLog
			},
			"logList button[id=lookLog]" : {//查看详细信息
				click : this.lookLog
			},
			"logView button[id=destroyV]" : {
				click : this.cancelOrReset
			},
			"logList button[id=queryLog]" : {//高级查询
				click : this.queryLog
			},
			"logList button[id=hideQ]" : {
				click : this.cancelOrReset
			},
			"logList button[id=resetQ]" : {
				click : this.cancelOrReset
			}
		})
	},
	queryLog : function(){
		var fv = Ext.getCmp("logQueryForm").getValues();
		var store = Ext.getCmp("logList").getStore();
		store.currentPage = 1;
		store.load({
			params : fv
		});
	},
	lookLog : function(){
		Ext.require('AM.view.LogView', function() {
			var sm = Ext.getCmp('logList').getSelectionModel();
			if (!sm.hasSelection()) {
				Ext.Msg.alert('提示', '请选择要查看的行');
				return;
			}
			var records = sm.getLastSelected();
			var baseFormWindow = Ext.getCmp("LogViewWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.LogView', {});// 第一次创建添加显示窗口
			}
			//当点击添加时加载
			baseFormWindow = Ext.getCmp("logViewWindow");
			baseFormWindow.show();
			var form = Ext.getCmp("logViewForm").getForm();
			form.loadRecord(records);// 将reocrd填充到表单中
		});
	},
	listResetLog : function(){
		var logQueryForm = Ext.getCmp("logQueryForm");
		if(logQueryForm){
			logQueryForm.reset();
		}
		var gridPanel = Ext.getCmp("logList");
		var store = gridPanel.getStore();
		store.currentPage = 1;
		store.load({
			params:{}
		});
	}, 
//	topQueryLog : function(){
//		Ext.require('AM.view.LogQuery', function() {
//			var baseFormWindow = Ext.getCmp("logQueryWindow");
//			if (null == baseFormWindow) {
//				Ext.create('AM.view.LogQuery', {});// 第一次创建添加显示窗口
//			}
//			//当点击查询时加载
//			Ext.getCmp("operationQ").getStore().reload();
//			baseFormWindow = Ext.getCmp("logQueryWindow");
//			baseFormWindow.setTitle("高级查询");
//			baseFormWindow.show();
//		});
//	},
	cancelOrReset : function(btn){
		if(btn.getId() == "hideQ"){
			Ext.getCmp("hideQ").up("window").hide();
		}else if(btn.getId() == "resetQ"){
			Ext.getCmp("resetQ").up("window").down("form").getForm().reset()
		}else if(btn.getId() == "destroyV"){
			Ext.getCmp("destroyV").up("window").destroy();
		}
	}
});
		
		
		