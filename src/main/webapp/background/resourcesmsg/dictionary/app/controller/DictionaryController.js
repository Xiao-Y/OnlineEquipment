Ext.define("AM.controller.DictionaryController",{
	extend : "Ext.app.Controller",
	views : ["DictionaryList","DictionaryMaintain"],
	models : ["DictionaryModel"],
	stores : ["DictionaryStore","ModelBoxStore","FieldBoxStore","KeyValueStore"],
	init : function() {
		this.control({
			"dictionaryList button[id=maintainDictionary]":{//数据字典维护
				click : this.maintainDictionary
			},
			"dictionaryMaintain button[id=cancel]":{//关闭操作
				click:this.cancelOrReset
			},
			"dictionaryMaintain button[id=addKeyValue]" : {//添加键值
				click : this.addKeyValue
			},
			"dictionaryMaintain button[id=removeKeyValue]" : {//删除键值
				click : this.removeKeyValue
			},
			"dictionaryMaintain button[id=removeField]" : {//删除字段
				click : this.removeField
			},
			"dictionaryMaintain button[id=removeModel]" : {//删除模块
				click : this.removeModel
			},
			"dictionaryMaintain button[id=saveDictionary]" : {//保存维护数据字典
				click : this.saveDictionary
			}
		});
	},
	maintainDictionary : function(){
		Ext.require('AM.view.DictionaryMaintain', function() {
			var baseFormWindow = Ext.getCmp("dictionaryMaintainWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.DictionaryMaintain', {});// 第一次创建添加显示窗口
			}
			Ext.getCmp("modelCodeBox").getStore().load();// 刷新模块下拉框;
			//当点击时加载
			baseFormWindow = Ext.getCmp("dictionaryMaintainWindow");
			baseFormWindow.setTitle("维护数据字典");
			baseFormWindow.show();
		});
	},
	addKeyValue : function(){
		var keyValueStore = Ext.getCmp("keyValueList").getStore();
		//如果最后一个为空或者最后一个中的某个为空
		if(keyValueStore.last() == null || (keyValueStore.last().get("displayField") && keyValueStore.last().get("valueField") && keyValueStore.last().get("notice"))){
			//实例化Record对象，并赋予各字段初始值
			var rec = [{
	            'displayField': '',
	            'valueField': '',
	            'notice': '',
	            "createTime" : ""//用于标识，是新添加的行
	        }];
	        //在最后一行后插入
			keyValueStore.insert(keyValueStore.getCount(), rec);
		}
	},
	removeKeyValue : function(){
		var sm = Ext.getCmp('keyValueList').getSelectionModel();
		if (!sm.hasSelection()) {
			Ext.Msg.alert('提示', '请选择要删除的行');
			return;
		}
		Ext.Msg.confirm('提示', '确定要删除所选的行？', function(btn) {
			if (btn == 'yes') {
				var records = sm.getSelection();
				var keyValueStore = Ext.getCmp("keyValueList").getStore();
				
				var i = 0;
				var keyValues = new Array();
				Ext.each(records,function(record){
					//如果不是新添加的加入到数组中使用ajax删除，否则新添加的直接删除
					if(record.get("createTime") != "" && record.get("createTime") != null){
						keyValues[i] = record.getData();
						i++;
					}else{
						keyValueStore.remove(record);
					}
				});

				//如果keyValue不为空后台删除
				if(keyValues != null && keyValues != ""){
					Ext.Ajax.request({
						url : '../dictionary/deleteDictionaryKeyValue',
						params : {
							"keyValues" : Ext.JSON.encode(keyValues)//删除的keyvalue值
						},
						method : 'POST',
						async : false,
						success : function(resopnse) {
							var jsonObj = Ext.JSON.decode(resopnse.responseText);
							Ext.Msg.alert('提示', jsonObj.message);
							if (jsonObj.success == true) {
								Ext.getCmp('keyValueList').getStore().reload();// 刷新表格
							}
						}
					});
				}
			}
		});
	},
	removeField : function(){
		var form = Ext.getCmp("dictionaryMaintainForm").getForm();
		var modelCodeBox = form.findField("modelCodeBox").getValue();//获取模块下拉列表中选种的值
		var fieldCodeBox = form.findField("fieldCodeBox").getValue();//获取字段下拉列表中选种的值
		if(modelCodeBox != "" && modelCodeBox != null && fieldCodeBox != "" && fieldCodeBox != null){
			Ext.Msg.confirm('提示', '删除所选字段的同时会删除其下的键值对，确定要删除所选的字段？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : '../dictionary/deleteDictionaryField',
						params : {
							"modelCodeBox" : modelCodeBox,
							"fieldCodeBox" : fieldCodeBox
						},
						method : 'POST',
						async : false,
						success : function(resopnse) {
							var jsonObj = Ext.JSON.decode(resopnse.responseText);
							Ext.Msg.alert('提示', jsonObj.message);
							if (jsonObj.success == true) {
								// 刷新模块下拉框;
								Ext.getCmp("modelCodeBox").getStore().load();
								//刷新键值对列表
								Ext.getCmp("keyValueList").getStore().reload();
								//刷新数据字典列表
								Ext.getCmp("dictionaryList").getStore().load();
								//清除modelCode
					        	Ext.getCmp("modelCodeReadOnly").setValue();
								//清除fieldCodel
					        	Ext.getCmp("fieldCodeReadOnly").setValue();
					        	//清除新model名称
					        	Ext.getCmp("newModelName").setValue();
					        	//清除新field名称
					        	Ext.getCmp("newFieldName").setValue();
							}
						}
					});	
				}
			});
		}else{
			Ext.Msg.alert('提示', "请选择某个模块下要删除的字段！");	
		}
	},
	removeModel : function(){
		var form = Ext.getCmp("dictionaryMaintainForm").getForm();
		var modelCodeBox = form.findField("modelCodeBox").getValue();//获取模块下拉列表中选种的值
		if(modelCodeBox != "" && modelCodeBox != null){
			Ext.Msg.confirm('提示', '删除所选模块的同时会删除其下的字段和键值对，确定要删除所选的字段？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : '../dictionary/deleteDictionaryModel',
						params : {
							"modelCodeBox" : modelCodeBox
						},
						method : 'POST',
						async : false,
						success : function(resopnse) {
							var jsonObj = Ext.JSON.decode(resopnse.responseText);
							Ext.Msg.alert('提示', jsonObj.message);
							if (jsonObj.success == true) {
								// 刷新模块下拉框;
								Ext.getCmp("modelCodeBox").getStore().load();
								//刷新键值对列表
								Ext.getCmp("keyValueList").getStore().reload();
								//刷新数据字典列表
								Ext.getCmp("dictionaryList").getStore().load();
								//清除modelCode
					        	Ext.getCmp("modelCodeReadOnly").setValue();
								//清除fieldCodel
					        	Ext.getCmp("fieldCodeReadOnly").setValue();
					        	//清除新model名称
					        	Ext.getCmp("newModelName").setValue();
					        	//清除新field名称
					        	Ext.getCmp("newFieldName").setValue();
							}
						}
					});	
				}
			});
		}else{
			Ext.Msg.alert('提示', "请选择要删除的模块！");	
		}
	},
	saveDictionary : function(){
		var form = Ext.getCmp("dictionaryMaintainForm").getForm();
		//模块的
		var modelCodeBox = form.findField("modelCodeBox").getValue();//获取模块下拉列表中选种的值
		var modelName = form.findField("modelCodeBox").getRawValue();//获取模块下拉列表中选种的原始值
		var modelCode = form.findField("modelCode").getValue();//获取模块Code值
		var newModelName = form.findField("newModelName").getValue();//获取新的模块名字
		//字段的
		var fieldCodeBox = form.findField("fieldCodeBox").getValue();//获取字段下拉列表中选种的值
		var fieldName = form.findField("fieldCodeBox").getRawValue();//获取字段下拉列表中选种的原始值
		var fieldCode = form.findField("fieldCode").getValue();//获取字段Code值
		var newFieldName = form.findField("newFieldName").getValue();//获取新的字段名字
		
		if(modelCodeBox == "" || modelCodeBox == null){//添加模块
			if(modelCode == ""){
				Ext.Msg.alert('提示', '请填写模块CODE');
				return false;
			}
			if(newModelName == ""){
				Ext.Msg.alert('提示', '请填写模块名称');
				return false;
			}
		}
		
		if(fieldCodeBox == "" || fieldCodeBox == null){//添加新字段
			if(fieldCode == ""){
			Ext.Msg.alert('提示', '请填写字段CODE');
			return false;
			}
			if(newFieldName == ""){
				Ext.Msg.alert('提示', '请填写字段名称');
				return false;
			}
		}
		//校验键值对是否填写完整-------start
		var keyValueStore = Ext.getCmp("keyValueList").getStore();
		var models = keyValueStore.getModifiedRecords();//获取所有更改的
		var flag = false;
		Ext.each(models,function(model){
			var displayField = model.get("displayField");
			var valueField = model.get("valueField");
			var notice = model.get("notice");
			//判断keyValue中是否有空值
			if(displayField == "" || valueField == "" || notice == ""){
				flag = true;
				return false;
			}
		});
		if(flag){
			Ext.Msg.alert('提示', '请填写完整后提交');
			return false;
		}
		var keyValues = new Array();
		for(var i = 0; i < models.length; i++){
			//model中的数据放入到数组
			keyValues[i] = models[i].getData();
		}
		//校验键值对是否填写完整-------end
		
		//新添加时至少要添加行键值对
		if(keyValueStore.getCount() < 1){
			Ext.Msg.alert('提示', '请至少填写一个键值对！');
			return false;
		}
		
		Ext.Ajax.request({
			url : "../dictionary/saveDictionary",
			params : {
				"modelCodeBox" : modelCodeBox,//模块下拉列表的值
				"modelCode" : modelCode,//模块CODE
				"modelName" : modelName,//原始模块名称
				"newModelName" : newModelName,//新的模块名
				"fieldCodeBox" : fieldCodeBox,//字段下拉列表中的值
				"fieldCode" : fieldCode,//字段CODE
				"fieldName" : fieldName,//原始字段名称
				"newFieldName" : newFieldName,//新字段名
				"keyValues" : Ext.JSON.encode(keyValues)//修改的keyvalue值
			},
			method : 'POST',
			async : false,//异步
			success : function(response) {
				var jsonObj = Ext.JSON.decode(response.responseText);
				if (jsonObj.success) {
					// 刷新模块下拉框;
					Ext.getCmp("modelCodeBox").setValue();
					Ext.getCmp("modelCodeBox").getStore().load();
					//刷新键值对列表
					keyValueStore.load();
					//刷新数据字典列表
					Ext.getCmp("dictionaryList").getStore().load();
//					//移除列表中的数据
//		        	Ext.getCmp("keyValueList").getStore().removeAll();
					//清除modelCode
		        	Ext.getCmp("modelCodeReadOnly").setValue();
					//清除fieldCodel
		        	Ext.getCmp("fieldCodeReadOnly").setValue();
		        	//清除model名称
		        	Ext.getCmp("newModelName").setValue();
		        	//清除field名称
		        	Ext.getCmp("newFieldName").setValue();
				}
				Ext.Msg.alert('提示', jsonObj.message);
			},
			failure : function(response) {
				var jsonObj = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert('提示', jsonObj.message);
			}
		});
	},
	cancelOrReset : function(btn){
		Ext.getCmp("newModelName").setValue();
		Ext.getCmp("newFieldName").setValue();
		//修改窗口销毁为隐藏
		Ext.getCmp("dictionaryMaintainWindow").hide();
	}
});