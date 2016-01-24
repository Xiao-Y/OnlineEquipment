Ext.define('AM.controller.ZipController', {
	extend : 'Ext.app.Controller',
	views : [ 'ZipList',"ZipAdd"],
	stores : [ 'ZipStore',"AreaStore","CityStore","ProvinceStore"],
	models : [ 'ZipModel' ],
	init : function() {
		this.control({
			"zipList button[id=exportZip]" : {//导出数据
				click : this.exportZip
			},
			"zipList button[id=importZip]" : {//打开导入窗口
				click : this.importZip
			},
			"zipList button[id=listResetZip]" : {
				click : this.listResetZip
			},
			"zipList button[id=queryZip]" : {
				click : this.queryZip
			},
			"zipAdd button[id=saveZip]" : {//保存导入数据
				click : this.saveZip
			},
			"zipAdd button[id=cancel]" : {
				click : this.cancelOrReset
			}
		})
	},
	queryZip : function(){
		var fv = Ext.getCmp("zipQueryForm").getValues();
		var store = Ext.getCmp("zipList").getStore();
		store.currentPage = 1;//页面从头开始
		store.load({
			params : fv
		});
	},
	listResetZip : function(){
		Ext.getCmp("zipQueryForm").getForm().reset();
		var gridPanel = Ext.getCmp("zipList");
		var store = gridPanel.getStore();
		store.currentPage = 1;//页面从头开始
		store.load({
			params:{}//查询参数
		});
	}, 
	importZip : function(){
		Ext.require('AM.view.ZipAdd', function() {
			var baseFormWindow = Ext.getCmp("zipAddWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.ZipAdd', {});// 第一次创建添加显示窗口
			}
			baseFormWindow = Ext.getCmp("zipAddWindow");
			baseFormWindow.setTitle("导入Excel");
			baseFormWindow.show();
		});
	},
	saveZip : function(){
		var form = Ext.getCmp("zipAddForm").getForm();
		if(form.isValid()){
			form.submit({
				url : "../zip/importZip",
				waitMsg : '正在导入数据...',
				success:function(form, action){
					var obj = Ext.JSON.decode(action.response.responseText);
					if(obj.success){
						Ext.getCmp("zipAddWindow").destroy();
						var store = Ext.getCmp("zipList").getStore();
						store.reload();
					}
					Ext.Msg.alert(obj.hint, obj.message);
				},
				failure:function(form, action){
					var obj = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert(obj.hint, obj.message)
				}
			});
		}
	},
	exportZip : function(){//导出
		window.location.href = "../zip/exportZip";
	},
	cancelOrReset : function(btn){
		if(btn.getId() == "cancel"){
			Ext.getCmp("cancel").up("window").hide();
		}else if(btn.getId() == "reset"){
			Ext.getCmp("reset").up("window").down("form").getForm().reset()
		}
	}
});
		
		
		