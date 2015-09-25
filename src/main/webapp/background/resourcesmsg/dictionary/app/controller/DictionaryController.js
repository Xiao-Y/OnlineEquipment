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
			}
		});
	},
	maintainDictionary : function(){
		Ext.require('AM.view.DictionaryMaintain', function() {
			var baseFormWindow = Ext.getCmp("dictionaryMaintainWindow");
			if (null == baseFormWindow) {
				Ext.create('AM.view.DictionaryMaintain', {});// 第一次创建添加显示窗口
			}
			var modelBoxStore = Ext.getCmp("modelCode").getStore();
			//在已有的store中添加一列
			modelBoxStore.load({
   				callback: function(records, options, success){ 
					modelBoxStore.insert(0,{"modelName":"新增","modelCode":""});
   				}
   			});// 刷新子模块下拉框
			//当点击时加载
			baseFormWindow = Ext.getCmp("dictionaryMaintainWindow");
			baseFormWindow.setTitle("维护数据字典");
			baseFormWindow.show();
		});
	},
	addKeyValue : function(){
		var keyValueStore = Ext.getCmp("keyValueList").getStore();
		if(keyValueStore.last() == null || (keyValueStore.last().get("displayField") && keyValueStore.last().get("valueField") && keyValueStore.last().get("notice"))){
			var rec = [{  //实例化Record对象，并赋予各字段初始值
	            'displayField': '',
	            'valueField': '',
	            'notice': ''
	        }];
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
		Ext.each(records,function(record){
			//先通过ajax从后台删除数据，删除成功后再从页面删除数据
			keyValueStore.remove(record);
		})
	},
	cancelOrReset : function(btn){
		if(btn.getId() == "cancel"){
			Ext.getCmp("cancel").up("window").destroy();
		}else if(btn.getId() == "reset"){
			Ext.getCmp("reset").up("window").down("form").getForm().reset()
		}
	}
});