Ext.define("AM.controller.RoleController",{
	extend : "Ext.app.Controller",
	views : ["RoleList","RoleAuthorize"],
	stores : ["RoleStore","RoleTreeStore"],
	models : ["RoleModel","RoleTreeModel"],
	init : function(){
		this.control({
			"roleList button[id=authorize]" : {
				click : function(){
					Ext.require('AM.view.RoleAuthorize', function() {
						var baseFormWindow = Ext.getCmp("authorizeWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.RoleAuthorize', {});
						}
						//当点击授权时加载
						//Ext.getCmp("roleTreeView").getStore().reload();
						baseFormWindow = Ext.getCmp("authorizeWindow");
						baseFormWindow.setTitle("授权管理");
						baseFormWindow.show();
					});
				}
			}
		});
	}
});