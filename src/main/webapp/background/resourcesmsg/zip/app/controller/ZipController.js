Ext.define('AM.controller.ZipController', {
	extend : 'Ext.app.Controller',
	views : [ 'ZipList'],
	stores : [ 'ZipStore'],
	models : [ 'ZipModel' ],
	init : function() {
		this.control({
			"zipList button[id=exportZip]" : {//导出数据
				click : this.exportZip
			},
//			"noticeList button[id=addNatice]" : {//打开查询窗口
//				click : this.addNatice
//			},
//			"noticeList button[id=editNotice]" : {//打开查询窗口
//				click : this.editNatice
//			},
			"noticeList button[id=listResetZip]" : {
				click : this.listResetNotice
			}
//			"noticeList button[id=lookNotice]" : {//查看详细信息
//				click : this.lookNotice
//			},
//			"noticeList button[id=delNotice]" : {//删除公告
//				click : this.delNotice
//			},
//			"noticeView button[id=cancel]" : {
//				click : this.cancelOrReset
//			},
//			"noticeQuery button[id=queryNotice]" : {//高级查询
//				click : this.queryNotice
//			},
//			"noticeQuery button[id=cancel]" : {
//				click : this.cancelOrReset
//			},
//			"noticeQuery button[id=reset]" : {
//				click : this.cancelOrReset
//			},
//			"noticeAdd button[id=reset]" : {
//				click : this.cancelOrReset
//			},
//			"noticeAdd button[id=cancel]" : {
//				click : this.cancelOrReset
//			},
//			"noticeAdd button[id=saveNotice]" : {
//				click : this.saveNotice
//			}
		})
	},
//	queryNotice : function(){
//		var fv = Ext.getCmp("noticeQueryForm").getValues();
//		var store = Ext.getCmp("noticeList").getStore();
//		store.load({
//			params : fv
//		});
//	},
//	lookNotice : function(){
//		Ext.require('AM.view.NoticeView', function() {
//			var sm = Ext.getCmp('noticeList').getSelectionModel();
//			if (!sm.hasSelection()) {
//				Ext.Msg.alert('提示', '请选择要查看的行');
//				return;
//			}
//			var records = sm.getLastSelected();
//			var baseFormWindow = Ext.getCmp("NoticeViewWindow");
//			if (null == baseFormWindow) {
//				Ext.create('AM.view.NoticeView', {});// 第一次创建添加显示窗口
//			}
//			//当点击添加时加载
//			baseFormWindow = Ext.getCmp("noticeViewWindow");
//			baseFormWindow.show();
//			var form = Ext.getCmp("noitceViewForm").getForm();
//			form.loadRecord(records);// 将reocrd填充到表单中
//		});
//	},
	listResetZip : function(){
		var gridPanel = Ext.getCmp("zipList");
		var store = gridPanel.getStore();
		store.load({
			params:{}
		});
	}, 
//	topQueryNotice : function(){
//		Ext.require('AM.view.NoticeQuery', function() {
//			var baseFormWindow = Ext.getCmp("noticeQueryWindow");
//			if (null == baseFormWindow) {
//				Ext.create('AM.view.NoticeQuery', {});// 第一次创建添加显示窗口
//			}
//			baseFormWindow = Ext.getCmp("noticeQueryWindow");
//			baseFormWindow.setTitle("高级查询");
//			baseFormWindow.show();
//		});
//	},
//	addNatice : function(){
//		Ext.require('AM.view.NoticeAdd', function() {
//			var baseFormWindow = Ext.getCmp("noticeAddWindow");
//			if (null == baseFormWindow) {
//				Ext.create('AM.view.NoticeAdd', {});// 第一次创建添加显示窗口
//			}
//			baseFormWindow = Ext.getCmp("noticeAddWindow");
//			baseFormWindow.setTitle("添加公告");
//			baseFormWindow.show();
//		});
//	},
	exportZip : function(){
		window.location.href = "../zip/exportZip";
	}
//	delNotice : function(){
//		var sm = Ext.getCmp("noticeList").getSelectionModel();
//		if(!sm.hasSelection()){
//			Ext.Msg.alert('提示', '请选择要删除的行');
//			return;
//		}
//		Ext.Msg.confirm('提示', '确定要删除所选的行？', function(btn) {
//			if(btn == "yes" ){
//				var record = sm.getLastSelected();
//				var id = record.get("id");
//				Ext.Ajax.request({
//					url : "../notice/deleteNoticeById/" + id,
//					method : "POST",
//					async : false,
//					success : function(response){
//						var obj = Ext.decode(response.responseText);
//						Ext.Msg.alert(obj.hint,obj.message);
//						if(obj.success){
//							Ext.getCmp("noticeList").getStore().reload();
//						}
//					},
//					failuer : function(response){
//						Ext.Msg.alert("提示","系统错误，请稍后重试！");
//					}
//				});
//			}
//		})
//	},
//	editNatice : function(btn) {
//		Ext.require("AM.view.NoticeAdd", function() {
//			var sm = Ext.getCmp('noticeList').getSelectionModel();
//			if (!sm.hasSelection()) {
//				Ext.Msg.alert('提示', '请选择要修改的行');
//				return;
//			}
//			var records = sm.getLastSelected();
//
//			var baseFormWindow = Ext.getCmp('noticeAddWindow');
//			if (null == baseFormWindow) {
//				Ext.create('AM.view.NoticeAdd', {});// 第一次创建添加显示窗口
//			}
//			baseFormWindow = Ext.getCmp('noticeAddWindow');
//			baseFormWindow.setTitle("编辑公告");
//			baseFormWindow.show();
//
//			var form = Ext.getCmp("noticeAddForm").getForm();
//			form.loadRecord(records);// 将reocrd填充到表单中
//		});
//	},
//	cancelOrReset : function(btn){
//		if(btn.getId() == "cancel"){
//			Ext.getCmp("cancel").up("window").destroy();
//		}else if(btn.getId() == "reset"){
//			Ext.getCmp("reset").up("window").down("form").getForm().reset()
//		}
//	}
});
		
		
		