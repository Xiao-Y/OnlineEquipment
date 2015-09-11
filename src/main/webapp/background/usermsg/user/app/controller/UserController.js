Ext.define("AM.controller.UserController",{
	extend : "Ext.app.Controller",
	views : ["UserList","UserAdd"],
	models : ["UserModel","ZipModel","RoleModel"],
	stores : ["UserStore","ProvinceStore","CityStore","AreaStore","RoleStore"],
	init : function(){
		this.control({
			"userList button[id=addUser]" : {
				click : function() {
					Ext.require('AM.view.UserAdd', function() {
						var baseFormWindow = Ext.getCmp("userAddWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.UserAdd', {});// 第一次创建添加显示窗口
						}
						//当点击添加时加载
						Ext.getCmp("province").getStore().reload();
						Ext.getCmp("roleId").getStore().reload();
						baseFormWindow = Ext.getCmp("userAddWindow");
						baseFormWindow.setTitle("添加用户信息");
						baseFormWindow.show();
					});
				}
			},
			"userAdd button[id=saveUser]" : {
				click : this.saveUser
			},
			"userList button[id=delUser]" : {
				click : this.delUser
			}
		});
	},
	saveUser : function(){
		var form = Ext.getCmp("userAddForm").getForm();
		if(form.isValid()){
			var fv = form.getValues();
			var url = "";
			if(fv.id){
				url = "../user/updateUser";
			}else{
				url = "../user/saveUser";
			}
			Ext.Ajax.request({
				url : url,
				params : fv,
				method : "POST",
				async : false,
				success : function(response){
					var obj = Ext.JSON.decode(response.responseText);
					if(obj.success){
						Ext.getCmp('userAddWindow').destroy();
						var gridPanel = Ext.getCmp("userList");
						var store = gridPanel.getStore();
						store.reload();
					}
					Ext.Msg.alert(obj.hint,obj.message);
				},
				failuer : function(response) {
					var obj = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert(obj.hint,obj.message);
				}
			});
		}
	},
	delUser : function(){
		var sm = Ext.getCmp("userList").getSelectionModel();
		if(!sm.hasSelection()){
			Ext.Msg.alert('提示', '请选择要删除的行');
			return;
		}
		Ext.Msg.confirm('提示', '确定要删除所选的行？', function(btn) {
			if(btn == "yes" ){
				var record = sm.getLastSelected();
				var id = record.get("id");
				Ext.Ajax.request({
					url : "../user/deleteUserById/" + id,
					method : "POST",
					async : false,
					success : function(response){
						var obj = Ext.decode(response.responseText);
						Ext.Msg.alert(obj.hint,obj.message);
						if(obj.success){
							Ext.getCmp("userList").getStore().reload();
						}
					},
					failuer : function(response){
						Ext.Msg.alert("提示","系统错误，请稍后重试！");
					}
				});
			}
		})
	}
});