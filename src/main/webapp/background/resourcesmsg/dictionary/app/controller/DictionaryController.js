Ext.define("AM.controller.DictionaryController",{
	extend : "Ext.app.Controller",
	views : ["DictionaryList","DictionaryMaintain"],
	models : ["DictionaryModel"],
	stores : ["DictionaryStore","ModelBoxStore","FieldBoxStore"],
	init : function() {
		this.control({
			"dictionaryList button[id=maintainDictionary]":{//数据字典维护
				click : this.maintainDictionary
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
			modelBoxStore.insert(0,{"modelName":"新增","modelCode":""});
			//当点击时加载
			baseFormWindow = Ext.getCmp("dictionaryMaintainWindow");
			baseFormWindow.setTitle("维护数据字典");
			baseFormWindow.show();
		});
	}
});