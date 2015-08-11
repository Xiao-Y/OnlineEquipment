Ext.define('AM.controller.MenuController', {
	extend : 'Ext.app.Controller',
	views : [ 'MenuList', 'MenuAdd', 'MenuQuery' ],
	stores : [ 'MenuStore', 'ParentMenuStore','MenuTypeStore' ],
	models : [ 'MenuModel' ],
	init : function() {
		this.control({
			'menuList button[id=addMenu]' : {
				click : function() {
					Ext.require('AM.view.MenuAdd', function() {
						var baseFormWindow = Ext.getCmp("menuAddWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.MenuAdd', {});// 第一次创建添加显示窗口
							console.log('创建窗口');
						}
						//当点击添加时加载
						Ext.getCmp("parentId").getStore().reload();
						baseFormWindow = Ext.getCmp("menuAddWindow");
						baseFormWindow.show();
					});
				}
			},
			// 高级查询
			'menuList button[id=topQueryMenu]' : {
				click : function() {
					Ext.require('AM.view.MenuQuery', function() {
						var baseFormWindow = Ext.getCmp("menuQueryWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.MenuQuery', {});// 第一次创建添加显示窗口
							console.log('创建窗口');
						}
						//当点击查询时加载
						Ext.getCmp("parentId").getStore().reload();
						baseFormWindow = Ext.getCmp("menuQueryWindow");
						baseFormWindow.show();
					});
				}
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
			'menuAdd button[id=cancelMenu]' : {
				click : function() {
					Ext.getCmp('menuAddWindow').destroy();
				}
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
			'menuAdd button[id=resetMenu]' : {
				click : this.resetMenu
			},// 重置
			'menuList button[id=listResetMenu]' : {
				click : this.listResetMenu
			},
			// 重置
			'menuQuery button[id=resetMenu]' : {
				click : this.resetMenu
			},
			// 取消
			'menuQuery button[id=cancelMenu]' : {
				click : function() {
					Ext.getCmp('menuQueryWindow').destroy();
				}
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
	queryMenu : function(btn) {
		var form = Ext.getCmp('menuQueryForm').getForm();
		var fv = form.getValues();
		Ext.getCmp('menuQueryWindow').destroy();
		var gridPanel = Ext.getCmp("menuList");
		var store = gridPanel.getStore();
		store.load({
			params:fv
		});
	},
	// 重置
	resetMenu : function(btn) {
		var saveFormPanel = Ext.getCmp("menuAddForm");
		var baseForm = saveFormPanel.getForm();
		baseForm.reset();
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
				var sel = sm.getSelection();
				var selectedId = sel[0].data.id;
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
			var id = records.internalId;

			var baseFormWindow = Ext.getCmp('menuAddWindow');
			if (null == baseFormWindow) {
				Ext.create('AM.view.MenuAdd', {});// 第一次创建添加显示窗口
				console.log('创建窗口');
			}
			baseFormWindow = Ext.getCmp('menuAddWindow');
			baseFormWindow.show();

			var form = Ext.getCmp("menuAddForm").getForm();
			form.loadRecord(records);// 将reocrd填充到表单中
		});
	}
});