Ext.define("AM.controller.UserController",{
	extend : "Ext.app.Controller",
	views : ["UserList","UserAdd"],
	models : ["UserModel","ZipModel","RoleModel"],
	stores : ["UserStore","ProvinceStore","CityStore","AreaStore","RoleStore"],
	init : function(){
		this.control({
			"userList button[id=addUser]" : {
				click : this.addUser
			},
			"userAdd button[id=saveUser]" : {
				click : this.saveUser
			},
			"userAdd button[id=destroyAdd]" : {
				click : function(btn){
					Ext.getCmp("destroyAdd").up("window").destroy();
				}
			},
			"userAdd button[id=resetAdd]" : {
				click : function(btn){
					Ext.getCmp("resetAdd").up("window").down("form").getForm().reset()
				}
			},
			"userAdd button[id=saveUser]" : {
				click : this.saveUser
			},
			"userList button[id=editUser]" : {
				click : this.editUser
			},
			"userList button[id=delUser]" : {
				click : this.delUser
			},
			"userList button[id=listResetUser]" : {
				click : this.listResetUser
			},
//			"userList button[id=topQueryUser]" : {
//				click : this.topQueryUser
//			},
//			"userList button[id=hideQuery]" : {
//				click : function(btn){
//					Ext.getCmp("hideQuery").up("window").hide();
//				}
//			},
			"userList button[id=resetQuery]" : {
				click : function(btn){
					Ext.getCmp("resetQuery").up("window").down("form").getForm().reset()
				}
			},
			"userList button[id=queryUser]" : {
				click : this.queryUser
			}
		});
	},
	addUser : function() {
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
	},
	editUser : function(){
		Ext.require('AM.view.UserAdd', function() {
			var sm = Ext.getCmp("userList").getSelectionModel();
			if(!sm.hasSelection()){
				Ext.Msg.alert('提示', '请选择要修改的行');
				return;
			}
			var baseFormWindow = Ext.getCmp("userAddWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.UserAdd', {});// 第一次创建添加显示窗口
			}
			
			Ext.getCmp("province").getStore().reload();
			Ext.getCmp("roleId").getStore().reload();
			
			var record = sm.getLastSelected();
			var provinceNum = record.get("province");
			var cityStore = Ext.getCmp("city").getStore();
			cityStore.proxy.extraParams = {
				province : provinceNum
			};
			cityStore.currentPage = 1;
			cityStore.load();
			
			var cityNum = record.get("city");
			var areaStore = Ext.getCmp("area").getStore();
			areaStore.proxy.extraParams = {
				city : cityNum
			};
			areaStore.currentPage = 1;
			areaStore.load();
			var form = Ext.getCmp("userAddForm").getForm();
			form.loadRecord(record);// 将reocrd填充到表单中
			
			baseFormWindow = Ext.getCmp("userAddWindow");
			baseFormWindow.setTitle("修改用户信息");
			baseFormWindow.show();
		});
	},
	listResetUser : function(){
		var userQueryForm = Ext.getCmp("userQueryForm");
		if(userQueryForm){
			userQueryForm.reset();
		}
		var store = Ext.getCmp("userList").getStore();
		store.currentPage = 1;
		store.load({
			params : {}
		});
	},
	topQueryUser : function(){
		Ext.require('AM.view.UserQuery', function() {
				var baseFormWindow = Ext.getCmp("userQueryWindow");
				if (null == baseFormWindow) {
					Ext.create('AM.view.UserQuery', {});// 第一次创建添加显示窗口
				}
				//当点击添加时加载
				Ext.getCmp("province").getStore().reload();
				Ext.getCmp("roleId").getStore().reload();
				baseFormWindow = Ext.getCmp("userQueryWindow");
				baseFormWindow.setTitle("查询用户信息");
				baseFormWindow.show();
			});
	},
	queryUser : function(){
		var form = Ext.getCmp('userQueryForm').getForm();
		var fv = form.getValues();
		var store = Ext.getCmp("userList").getStore();
		store.currentPage = 1;
		store.load({
			params:fv
		});	
	}
});