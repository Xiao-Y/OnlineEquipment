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
	cancelOrReset : function(btn){
		if(btn.getId() == "cancel"){
			Ext.getCmp("cancel").up("window").destroy();
		}else if(btn.getId() == "reset"){
			Ext.getCmp("reset").up("window").down("form").getForm().reset()
		}
	}
});