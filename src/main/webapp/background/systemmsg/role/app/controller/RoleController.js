Ext.define("AM.controller.RoleController",{
	extend : "Ext.app.Controller",
	views : ["RoleList","RoleAdd","RoleAuthorize"],
	stores : ["RoleStore","RoleTreeStore"],
	models : ["RoleModel","RoleTreeModel"],
	init : function(){
		this.control({
			"roleList button[id=authorize]" : {
				click : this.authorize
			},
			"roleList button[id=addRole]" : {
				click : this.addRole
			},
			// 取消
			'roleAdd button[id=cancel]' : {
				click : this.cancelOrReset
			},
			// 重置
			'roleAdd button[id=reset]' : {
				click : this.cancelOrReset
			},
			// 保存
			'roleAdd button[id=saveRole]' : {
				click : this.saveRole
			},
			// 编辑
			'roleList button[id=editRole]' : {
				click : this.editRole
			},
			//关闭
			'authorizeSave button[id=cancel]' : {
				click : this.cancelOrReset
			},
			'authorizeSave button[id=saveAuthorize]' : {
				click : this.authorizeSave
			}
		});
	},
	authorize : function(){
		var check = checkSelected('roleList');
		if(!check){
			return;
		}
		Ext.require('AM.view.RoleAuthorize', function() {
			var baseFormWindow = Ext.getCmp("authorizeWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.RoleAuthorize', {});
			}
			baseFormWindow = Ext.getCmp("authorizeWindow");
			baseFormWindow.setTitle("授权管理");
			baseFormWindow.show();
		});
	},
	addRole : function(){
		Ext.require('AM.view.RoleAdd', function() {
			var baseFormWindow = Ext.getCmp("roleAddWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.RoleAdd', {});// 第一次创建添加显示窗口
			}
			baseFormWindow = Ext.getCmp("roleAddWindow");
			baseFormWindow.setTitle("添加角色");
			baseFormWindow.show();
		});
	},
	saveRole : function(){
		var form = Ext.getCmp("roleAddForm").getForm();
		if (form.isValid()) {
			var fv = form.getValues();
			var url = "";
			//根据id是否存在，判断是更新还是保存
			if(fv.id){
				url = "../role/updateRole";
			}else{
				url = '../role/saveRole';
			}
			form.submit({
				url : url,
				params : Ext.JSON.encode(fv),
				success : function(form, action) {
					var jsonObj = Ext.JSON.decode(action.response.responseText);
					if (jsonObj.success) {
						Ext.getCmp('roleAddWindow').destroy();
						var gridPanel = Ext.getCmp("roleList");
						var store = gridPanel.getStore();
						store.reload();
					}
					Ext.Msg.alert('提示', jsonObj.message);
				},
				failure : function(form, action) {
					var jsonObj = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert('提示', jsonObj.message);
				}
			});
		}	
	},
	editRole : function(){
		var id = 'roleList';
		var check = checkSelected(id);
		if(!check){
			return;
		}
		Ext.require("AM.view.RoleAdd", function() {
			var baseFormWindow = Ext.getCmp('roleAddWindow');
			if (null == baseFormWindow) {
				Ext.create('AM.view.RoleAdd', {});// 第一次创建添加显示窗口
			}
			baseFormWindow = Ext.getCmp('roleAddWindow');
			baseFormWindow.setTitle("编辑角色");
			baseFormWindow.show();

			var sm = Ext.getCmp(id).getSelectionModel();
			var records = sm.getLastSelected();
			var form = Ext.getCmp("roleAddForm").getForm();
			form.loadRecord(records);// 将reocrd填充到表单中
		});
		//编辑时设置角色CODE为只读
		Ext.getCmp("roleCode").setReadOnly(true);
	},
	cancelOrReset : function(btn){
		if(btn.getId() == "cancel"){
			Ext.getCmp("cancel").up("window").destroy();
		}else if(btn.getId() == "reset"){
			Ext.getCmp("reset").up("window").down("form").getForm().reset()
		}
	},
	//保存权限信息
	authorizeSave : function(){
		var form = Ext.getCmp("authorizeForm").getForm();
		form.submit({
			url : "../role/saveRoleAuthorize",
			success:function(form, action){
				var obj = Ext.JSON.decode(action.response.responseText);
				if(obj.success){
					Ext.getCmp("authorizeWindow").destroy();
				}
				Ext.Msg.alert(obj.hint, obj.message);
			},
			failure:function(form, action){
				var obj = Ext.JSON.decode(action.response.responseText);
				Ext.Msg.alert(obj.hint, obj.message)
			}
		});		
	}
});
/**
 * 检查是否选择某项
 * @param {} id 列表的id
 * @return {Boolean} false 没选择，true 钩选过
 */
function checkSelected(id){
	var sm = Ext.getCmp(id).getSelectionModel();
	if (!sm.hasSelection()) {
		Ext.Msg.alert('提示', '请选择要修改的行');
		return false;
	}
	return true;
}