Ext.define("AM.controller.UserController",{
	extend : "Ext.app.Controller",
	views : ["UserList","UserAdd"],
	models : ["UserModel"],
	stores : ["UserStore"],
	init : function(){
		this.control({
			"userList button[id=addUser]" : {
				click : function() {
					Ext.require('AM.view.UserAdd', function() {
						var baseFormWindow = Ext.getCmp("userAddWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.UserAdd', {});// 第一次创建添加显示窗口
							console.log('创建窗口');
						}
						//当点击添加时加载
//						Ext.getCmp("parentId").getStore().reload();
						baseFormWindow = Ext.getCmp("userAddWindow");
						baseFormWindow.setTitle("添加用户信息");
						baseFormWindow.show();
					});
				}
			}
		});
	}
});