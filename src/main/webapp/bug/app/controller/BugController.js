Ext.define('AM.controller.BugController', {
	extend : 'Ext.app.Controller',
	views : [ 'BugList',"BugAdd"],//, 'BugAdd', 'BugQuery' 
	stores : [ 'BugStore',"ParentMenuStore","ChildrenMenuStore","StatusStore","SeverityStore","ReappearStore","BugTypeStore","PriorityStore"],//
	models : [ 'BugModel',"MenuModel"],//
	init : function() {
		this.control({
			"bugList button[id=addBug]" : {
				click : function() {
					Ext.require('AM.view.BugAdd', function() {
						var baseFormWindow = Ext.getCmp("bugAddWindow");
						if (null == baseFormWindow) {
							Ext.create('AM.view.BugAdd', {});// 第一次创建添加显示窗口
							console.log('BugAdd创建窗口');
						}
						//当点击添加时加载
						Ext.getCmp("parentId").getStore().reload();
						baseFormWindow = Ext.getCmp("bugAddWindow");
						baseFormWindow.setTitle("添加BUG");
						baseFormWindow.show();
					});
				}
			},
			"bugAdd button[id=saveBug]":{
				click:this.saveBug
			}
		});
	},
	saveBug : function(but){
		var form = Ext.getCmp("bugAddForm").getForm();
		if(form.isValid()){
			var fv = form.getValues();
			console.info(fv);
			console.info(Ext.JSON.encode(fv));
			var url = "";
			if(fv.id){
				url = "../bug/updateBug";
			}else{
				url = "../bug/svaeBug";
			}
			Ext.Ajax.request({
				url : url,
				params : Ext.JSON.encode(fv),
				method : 'POST',
				async : false,
				headers : {
					"Content-Type" : "application/json; charset=utf-8"
				},
				success:function(response){
					var obj = Ext.JSON.decode(response.responseText);
					if(obj.success){
						Ext.getCmp("bugAddWindow").destroy();
						var store = Ext.getCmp("bugList").getStore();
						store.reload();
					}
					Ext.Msg.alert('提示', obj.message);
				},
				failure:function(response){
					var obj = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', obj.message);
				}
			});
		}
	}
});