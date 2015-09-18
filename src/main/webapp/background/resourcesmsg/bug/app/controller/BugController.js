Ext.define('AM.controller.BugController', {
	extend : 'Ext.app.Controller',
	views : [ 'BugList',"BugAdd", 'BugQuery',"BugView"],
	stores : [ 'BugStore',"ParentMenuStore","ChildrenMenuStore","StatusStore","SeverityStore","ReappearStore","BugTypeStore","PriorityStore"],//
	models : [ 'BugModel',"MenuModel"],//
	init : function() {
		this.control({
			"bugList button[id=addBug]" : {
				click : this.bugList
			},
			"bugAdd button[id=saveBug]":{//保存操作
				click:this.saveBug
			},
			"bugAdd button[id=reset]":{//重置操作
				click:this.cancelOrReset
			},
			"bugAdd button[id=cancel]":{//关闭操作
				click:this.cancelOrReset
			},
			"bugList button[id=editBug]":{//编辑操作
				click:this.editBug
			},
			"bugList button[id=delBug]" : {//删除操作
				click:this.delBug
			},
			"bugList button[id=lookBug]" : {//查看详细信息
				click:this.lookBug
			},
			"bugList button[id=listResetBug]" : {//清除查询条件，刷新列表
				click:this.listResetBug
			},
			"bugList button[id=topQueryBug]" : {//高级查询窗口
				click:this.topQueryBug
			},
			"bugList gridpanel[id=bugList]" : {//点击列表中的某一行触发
				itemclick:this.getImage
			},
			"bugQuery button[id=queryBug]" : {//高级查询
				click:this.queryBug
			},
			"bugQuery button[id=reset]" : {//高级查询时，重置查询条件
				click:this.cancelOrReset
			},
			'bugQuery button[id=cancel]' : {// 查询关闭
				click:this.cancelOrReset
			},
			'bugView button[id=cancel]' : {// 查询关闭
				click:this.cancelOrReset
			}
		});
	},
	bugList : 
		function() {//添加窗口
			Ext.require('AM.view.BugAdd', function() {
				var baseFormWindow = Ext.getCmp("bugAddWindow");
				if (null == baseFormWindow) {
					Ext.create('AM.view.BugAdd', {});// 第一次创建添加显示窗口
					console.log('BugAdd创建窗口');
				}
				//当点击添加时加载
				Ext.getCmp("parentId").getStore().reload();
				Ext.getCmp("bugType").getStore().reload();
				Ext.getCmp("reappear").getStore().reload();
				Ext.getCmp("severity").getStore().reload();
				Ext.getCmp("status").getStore().reload();
				baseFormWindow = Ext.getCmp("bugAddWindow");
				baseFormWindow.setTitle("添加BUG");
				baseFormWindow.show();
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
			}
			//当点击时加载
			Ext.getCmp("parentId").getStore().reload();
			Ext.getCmp("bugType").getStore().reload();
			Ext.getCmp("reappear").getStore().reload();
			Ext.getCmp("severity").getStore().reload();
			Ext.getCmp("status").getStore().reload();
			baseFormWindow = Ext.getCmp("bugAddWindow");
			baseFormWindow.setTitle("编辑BUG");
			baseFormWindow.show();
			var form = Ext.getCmp("bugAddForm").getForm();
			//通过父id查询其下的子菜单
			var store = Ext.getCmp("childrenId").getStore();
			store.proxy.extraParams = {
				"parentId" : records.get("parentId")
			}
			store.currentPage = 1;
			store.load();
			form.loadRecord(records);// 将reocrd填充到表单中
		});
	},
	lookBug : function(){
		Ext.require('AM.view.BugView', function() {
			var sm = Ext.getCmp('bugList').getSelectionModel();
			if (!sm.hasSelection()) {
				Ext.Msg.alert('提示', '请选择要查看的行');
				return;
			}
			var records = sm.getLastSelected();
			var baseFormWindow = Ext.getCmp("bugViewWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.BugView', {});// 第一次创建添加显示窗口
			}
			baseFormWindow = Ext.getCmp("bugViewWindow");
			baseFormWindow.setTitle("查看BUG详细信息");
			baseFormWindow.show();
			var selectedId = records.get("id");
			var form = Ext.getCmp("bugViewForm").getForm();
			
			form.load({
				 url: '../bug/getBugViewById/' + selectedId,
                waitMsg: '正在载入数据...',
                waitTitle: '请稍等...',
//                params: { t: "get", prid: prid },
//                success: function(_form, action) {
//					
//                },
                failure: function(_form, action) {
                    msg("提示信息", "加载数据时发生异常！");
                }
			});
			//加载图片
			//获取点击行的id
			var imgArray = new Array();
			Ext.Ajax.request({
				url:"../bug/getImage/" + selectedId,
				method:'GET',
				async:false,
				success:function(response){
					var obj = Ext.JSON.decode(response.responseText);
					//获取图片数组
					imgArray = obj.root;
				},
				failure:function(response){
					var obj = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert('提示',obj.message);
				}
			});
			//移除页面板上的图片		
			Ext.getCmp('bugViewImagePanel').removeAll();
			if(imgArray){
				for(var i = 0; i < imgArray.length; i++){
					(function(imageId){
						//创建image对象
						var changingImage = Ext.create('Ext.Img', {
							src: '../../../resource/upload/image/'+imgArray[i]
						});
						//添加到面板上
						Ext.getCmp('bugViewImagePanel').add(changingImage);
					})(imgArray[i])
				}
			}
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
			Ext.getCmp("bugType").getStore().reload();
			Ext.getCmp("reappear").getStore().reload();
			Ext.getCmp("severity").getStore().reload();
			Ext.getCmp("status").getStore().reload();
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
		//获取点击行的id
		var id = record.get('id');
		var imgArray = new Array();
		Ext.Ajax.request({
			url:"../bug/getImage/" + id,
			method:'GET',
			async:false,
			success:function(response){
				var obj = Ext.JSON.decode(response.responseText);
				//获取图片数组
				imgArray = obj.root;
			},
			failure:function(response){
				var obj = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert('提示',obj.message);
			}
		});
		//移除页面板上的图片		
		Ext.getCmp('bugImagePanel').removeAll();
		if(imgArray){
			for(var i = 0; i < imgArray.length; i++){
				(function(imageId){
					//创建image对象
					var changingImage = Ext.create('Ext.Img', {
						width:800,
						height:600,
						src: '../../../resource/upload/image/'+imgArray[i]
					});
					//添加到面板上
					Ext.getCmp('bugImagePanel').add(changingImage);
				})(imgArray[i])
			}
		}
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