Ext.define("AM.controller.RoleController",{
	extend : "Ext.app.Controller",
	views : ["RoleList","RoleTreeView","RoleAuthorize"],
	stores : ["RoleStore","RoleTreeStore"],
	models : ["RoleModel","RoleTreeModel"],
	init : function(){
		this.control({
			"roleList button[id=authorize]" : {
				click : function(){
					Ext.require('AM.view.RoleAuthorize', function() {
						var baseFormWindow = Ext.getCmp("authorizeWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.RoleAuthorize', {});// 第一次创建添加显示窗口
							console.log('创建窗口');
						}
						//当点击授权时加载
//						Ext.getCmp("roleTreeWindow").getStore().reload();
						baseFormWindow = Ext.getCmp("authorizeWindow");
						baseFormWindow.setTitle("授权管理");
						baseFormWindow.show();
					});
				}
			}
		});
	}
});