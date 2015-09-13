Ext.define('AM.controller.MenuController', {
	extend : 'Ext.app.Controller',
	views : [ 'MenuList', 'MenuAdd', 'MenuQuery' ],
	stores : [ 'MenuStore', 'ParentMenuStore','MenuTypeStore' ],
	models : [ 'MenuModel' ],
	init : function() {
		this.control({
			'menuList button[id=addMenu]' : {
				click : this.addMenu
			},
			// 高级查询
			'menuList button[id=topQueryMenu]' : {
				click : this.topQueryMenu
			},
			// 删除
			'menuList button[id=delMenu]' : {
				click : this.delMenu
			},
			// 编辑
			'menuList button[id=editMenu]' : {
				click : this.editMenu
			},
			// 取消
			'menuAdd button[id=cancel]' : {
				click : this.cancelOrReset
			},
			// 保存操作
			'menuAdd button[id=saveMenu]' : {
				click : this.saveMenu
			},
			// 查询操作
			'menuQuery button[id=queryMenu]' : {
				click : this.queryMenu
			},
			// 重置
			'menuAdd button[id=reset]' : {
				click : this.cancelOrReset
			},// 重置
			'menuList button[id=listResetMenu]' : {
				click : this.listResetMenu
			},
			// 重置
			'menuQuery button[id=reset]' : {
				click : this.cancelOrReset
			},
			// 取消
			'menuQuery button[id=cancel]' : {
				click : this.cancelOrReset
			}
		});
	},
	// 保存菜单
	saveMenu : function(btn) {
		var form = Ext.getCmp("menuAddForm").getForm();
		if (form.isValid()) {
			var fv = form.getValues();
			var url = "";
			//根据id是否存在，判断是更新还是保存
			if(fv.id){
				url = "../menu/updateMenu";
			}else{
				url = '../menu/saveMenu';
			}
			Ext.Ajax.request({
				url : url,
				params : Ext.JSON.encode(fv),
				method : 'POST',
				async : false,
				headers : {
					"Content-Type" : "application/json; charset=utf-8"
				},
				success : function(response) {
					var jsonObj = Ext.JSON.decode(response.responseText);
					if (jsonObj.success) {
						Ext.getCmp('menuAddWindow').destroy();
						var gridPanel = Ext.getCmp("menuList");
						var store = gridPanel.getStore();
						store.reload();
					}
					Ext.Msg.alert('提示', jsonObj.message);
				},
				failure : function(response) {
					var jsonObj = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', jsonObj.message);
				}
			});
		}
	},
	queryMenu : function(btn) {//查询
		var form = Ext.getCmp('menuQueryForm').getForm();
		var fv = form.getValues();
		//Ext.getCmp('menuQueryWindow').destroy();
		var gridPanel = Ext.getCmp("menuList");
		var store = gridPanel.getStore();
		store.load({
			params:fv
		});
	},// 重置
	listResetMenu : function(btn) {
		var gridPanel = Ext.getCmp("menuList");
		var store = gridPanel.getStore();
		store.load({
			params:{}
		});
	},
	// 删除
	delMenu : function() {
		var sm = Ext.getCmp('menuList').getSelectionModel();
		if (!sm.hasSelection()) {
			Ext.Msg.alert('提示', '请选择要删除的行');
			return;
		}

		Ext.Msg.confirm('提示', '确定要删除所选的行？', function(btn) {
			if (btn == 'yes') {
				//var sel = sm.getSelection();
				var record = sm.getLastSelected();
				var selectedId = record.get("id");
//				var selectedId = sel[0].data.id;
				Ext.Ajax.request({
					url : '../menu/deleteMenu/' + selectedId,
					method : 'POST',
					async : false,
					success : function(resopnse) {
						var jsonObj = Ext.JSON.decode(resopnse.responseText);
						Ext.Msg.alert('提示', jsonObj.message);
						if (jsonObj.success == true) {
							Ext.getCmp('menuList').getStore().reload();// 刷新表格
						}
					}
				});
			}
		});
	},
	// 编辑
	editMenu : function(btn) {
		Ext.require("AM.view.MenuAdd", function() {
			var sm = Ext.getCmp('menuList').getSelectionModel();
			if (!sm.hasSelection()) {
				Ext.Msg.alert('提示', '请选择要修改的行');
				return;
			}
			var records = sm.getLastSelected();
			//var id = records.internalId;

			var baseFormWindow = Ext.getCmp('menuAddWindow');
			if (null == baseFormWindow) {
				Ext.create('AM.view.MenuAdd', {});// 第一次创建添加显示窗口
			}
			baseFormWindow = Ext.getCmp('menuAddWindow');
			baseFormWindow.setTitle("编辑菜单");
			baseFormWindow.show();

			var form = Ext.getCmp("menuAddForm").getForm();
			form.loadRecord(records);// 将reocrd填充到表单中
		});
	},
	topQueryMenu : 
		function() {
			Ext.require('AM.view.MenuQuery', function() {
				var baseFormWindow = Ext.getCmp("menuQueryWindow");
				if (null == baseFormWindow) {
					Ext.create('AM.view.MenuQuery', {});// 第一次创建添加显示窗口
				}
				//当点击查询时加载
				Ext.getCmp("parentId").getStore().reload();
				baseFormWindow = Ext.getCmp("menuQueryWindow");
				baseFormWindow.show();
			});
		},
	addMenu : 
		function() {
			Ext.require('AM.view.MenuAdd', function() {
				var baseFormWindow = Ext.getCmp("menuAddWindow");
				if (null == baseFormWindow) {
					Ext.create('AM.view.MenuAdd', {});// 第一次创建添加显示窗口
				}
				//当点击添加时加载
				Ext.getCmp("parentId").getStore().reload();
				baseFormWindow = Ext.getCmp("menuAddWindow");
				baseFormWindow.setTitle("添加菜单");
				baseFormWindow.show();
			});
		},
	cancelOrReset : 
		function(btn){
			if(btn.getId() == "cancel"){
				Ext.getCmp("cancel").up("window").destroy();
			}else if(btn.getId() == "reset"){
				Ext.getCmp("reset").up("window").down("form").getForm().reset()
			}
		}
});