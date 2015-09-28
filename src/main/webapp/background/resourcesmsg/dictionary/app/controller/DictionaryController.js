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
			Ext.getCmp("modelCodeBox").getStore().load();// 刷新子模块下拉框;
			//当点击时加载
			baseFormWindow = Ext.getCmp("dictionaryMaintainWindow");
			baseFormWindow.setTitle("维护数据字典");
			baseFormWindow.show();
		});
	},
	addKeyValue : function(){
		var keyValueStore = Ext.getCmp("keyValueList").getStore();
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
		var records = sm.getSelection();
		var keyValueStore = Ext.getCmp("keyValueList").getStore();
		//先通过ajax从后台删除数据，删除成功后再从页面删除数据
		Ext.each(records,function(record){
			keyValueStore.remove(record);
		})
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
					keyValueStore.reload();
					Ext.getCmp("dictionaryList").getStore().load();
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