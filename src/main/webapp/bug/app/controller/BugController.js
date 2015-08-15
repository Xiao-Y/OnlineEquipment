Ext.define('AM.controller.BugController', {
	extend : 'Ext.app.Controller',
	views : [ 'BugList',"BugAdd", 'BugQuery'],
	stores : [ 'BugStore',"ParentMenuStore","ChildrenMenuStore","StatusStore","SeverityStore","ReappearStore","BugTypeStore","PriorityStore"],//
	models : [ 'BugModel',"MenuModel"],//
	init : function() {
		this.control({
			"bugList button[id=addBug]" : {
				click : function() {//添加窗口
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
			"bugAdd button[id=saveBug]":{//保存操作
				click:this.saveBug
			},
			"bugAdd button[id=resetBug]":{//重置操作
				click:function(){
					var form = Ext.getCmp("bugAddForm").getForm();
					form.reset();
				}
			},
			"bugAdd button[id=cancelBug]":{//关闭操作
				click:function() {
					Ext.getCmp('bugAddWindow').destroy();
				}
			},
			"bugList button[id=editBug]":{//编辑操作
				click:this.editBug
			},"bugList button[id=resetBug]":{//重置操作
				click:this.resetBug
			},"bugList button[id=cancelBug]":{//编辑操作
				click:this.cancelBug
			},
			"bugList button[id=delBug]" : {//删除操作
				click:this.delBug
			},
			"bugList button[id=listResetBug]" : {//清除查询条件，刷新列表
				click:this.listResetBug
			},
			"bugList button[id=topQueryBug]" : {//高级查询窗口
				click:this.topQueryBug
			},
			"bugList gridpanel[id=bugList]" : {
				itemclick:this.getImage
			},
			"bugQuery button[id=queryBug]" : {//高级查询
				click:this.queryBug
			},
			"bugQuery button[id=resetBug]" : {//高级查询时，重置查询条件
				click:function(){
					var form = Ext.getCmp("bugQueryForm").getForm();
					form.reset();
				}
			},
			'bugQuery button[id=cancelBug]' : {// 查询关闭
				click:function() {
					Ext.getCmp('bugQueryWindow').destroy();
				}
			}
		});
	},
	saveBug : function(){//添加/更新
		var form = Ext.getCmp("bugAddForm").getForm();
		if(form.isValid()){
			var fv = form.getValues();
			var url = "";
			if(fv.id){
				url = "../bug/updateBug";
			}else{
				url = "../bug/svaeBug";
			}
			
			form.submit({
				url : url,
				params : Ext.JSON.encode(fv),
				waitMsg : '正在保存图片...',
				success:function(form, action){
					var obj = Ext.JSON.decode(action.response.responseText);
					if(obj.success){
						Ext.getCmp("bugAddWindow").destroy();
						var store = Ext.getCmp("bugList").getStore();
						store.reload();
					}
					Ext.Msg.alert('提示', obj.message);
				},
				failure:function(form, action){
					var obj = Ext.JSON.decode(action.response.responseText);
					Ext.Msg.alert('提示', obj.message);
				}
			});
		}
	},
	editBug : function(){//回显
		Ext.require('AM.view.BugAdd', function() {
			var sm = Ext.getCmp('bugList').getSelectionModel();
			if (!sm.hasSelection()) {
				Ext.Msg.alert('提示', '请选择要修改的行');
				return;
			}
			var records = sm.getLastSelected();
			var baseFormWindow = Ext.getCmp("bugAddWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.BugAdd', {});// 第一次创建添加显示窗口
				console.log('BugAdd创建窗口');
			}
			//当点击添加时加载
			Ext.getCmp("parentId").getStore().reload();
			baseFormWindow = Ext.getCmp("bugAddWindow");
			baseFormWindow.setTitle("编辑BUG");
			baseFormWindow.show();
			var form = Ext.getCmp("bugAddForm").getForm();
			form.loadRecord(records);// 将reocrd填充到表单中
		});
	},
	delBug : function(){
		var sm = Ext.getCmp('bugList').getSelectionModel();
		if (!sm.hasSelection()) {
			Ext.Msg.alert('提示', '请选择要删除的行');
			return;
		}

		Ext.Msg.confirm('提示', '确定要删除所选的行？', function(btn) {
			if (btn == 'yes') {
				var sel = sm.getSelection();
				var selectedId = sel[0].data.id;
				Ext.Ajax.request({
					url : '../bug/deleteBug/' + selectedId,
					method : 'POST',
					async : false,
					success : function(resopnse) {
						var jsonObj = Ext.JSON.decode(resopnse.responseText);
						Ext.Msg.alert('提示', jsonObj.message);
						if (jsonObj.success == true) {
							Ext.getCmp('bugList').getStore().reload();// 刷新表格
						}
					}
				});
			}
		});
	},
	listResetBug : function(but){//重置，清空查询条件
		var store = Ext.getCmp("bugList").getStore();
		store.load({
			params:{}
		});
	},
	topQueryBug : function(){//高级查询窗口
		Ext.require('AM.view.BugQuery', function() {
			var baseFormWindow = Ext.getCmp("bugQueryWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.BugQuery', {});// 第一次创建添加显示窗口
				console.log('创建窗口');
			}
			//当点击查询时加载
			Ext.getCmp("parentId").getStore().reload();
			baseFormWindow = Ext.getCmp("bugQueryWindow");
			baseFormWindow.show();
		});
	},
	queryBug : function(){//高级查询
		var form = Ext.getCmp('bugQueryForm').getForm();
		var fv = form.getValues();
		//Ext.getCmp('bugQueryWindow').destroy();
		var store = Ext.getCmp("bugList").getStore();
		store.load({
			params:fv
		});
	},
	getImage : function(view, record, item, index,  e,  eOpts){
		var id = record.get('id');
		var imgArray = new Array();
		Ext.Ajax.request({
			url:"../bug/getImage/" + id,
			method:'GET',
			async:false,
			success:function(response){
				var obj = Ext.JSON.decode(response.responseText);
				console.info(obj);
				imgArray = obj.root;
			},
			failure:function(response){
				var obj = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert('提示',obj.message);
			}
		});
		
		Ext.getCmp('bugImage').removeAll();
		for(var i =0 ;i<imgArray.length;i++){
			(function(imageId){
				var changingImage = Ext.create('Ext.Img', {
					width:400,
					height:250,
					src: '../resource/upload/image/'+imgArray[i]
				});
				Ext.getCmp('bugImage').add(changingImage);
			})(imgArray[i])
		}
	}
});