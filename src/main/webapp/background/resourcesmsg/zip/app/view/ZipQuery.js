Ext.define('AM.view.ZipQuery', {
	extend : 'Ext.window.Window',
	alias : "widget.zipQuery",
	id : 'zipQueryWindow',
	height : 380,
	width : 550,
	layout : 'fit',
	modal:true,//模态框
	title:"高级查询",
	items : [ {
		xtype : 'form',
		id : 'zipQueryForm',
		buttonAlign : 'right',
		bodyPadding : 15,
		autoScroll : true,
		layout : 'anchor',
		defaults : {
			labelWidth : 90,
			labelAlign : 'right',
			anchor : '90%'
		},
		defaultType : 'textfield',
		items : [ {
			fieldLabel : '省/直辖市',
			xtype : 'combobox',
			displayField : 'name',
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'ProvinceStore',
			id : 'province',
			name : 'province',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>"
			},
			listeners: {
        		select:function(combo){
        			Ext.getCmp('area').getStore().removeAll();
        			Ext.getCmp('area').setValue("");
        			Ext.getCmp('city').getStore().removeAll();
    	   			Ext.getCmp('city').getStore().load({
    	   				params : {
    	   					province:combo.getValue()
    	   				}
    	   			});// 刷新下拉框
        		}
        	}
		}, {
			fieldLabel : '市/直辖县级',
			xtype : 'combobox',
			displayField : 'name',
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'CityStore',
			id : 'city',
			name : 'city',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>"
			},
			listeners: {
        		select:function(combo){
        			Ext.getCmp('area').getStore().removeAll();
    	   			Ext.getCmp('area').getStore().load({
    	   				params : {
    	   					city:combo.getValue()
    	   				}
    	   			});// 刷新子模块下拉框
        		}
        	}
		}, {
			fieldLabel : '区/县',
			xtype : 'combobox',
			displayField : 'name',
			valueField : 'id',
			queryMode : 'local',
			forceSelection : true,// 所选择的值必须是列表中的值
			store : 'AreaStore',
			id : 'area',
			name : 'area',
			listConfig : {// 下拉列表的样式
				emptyText : "<font color='red'>没有找到匹配项</font>"
			}
		},{
			fieldLabel : '行政码',
			name : 'id'
		},{
			fieldLabel : '地区名称',
			name : 'name'
		},{
			fieldLabel : '上级行政码',
			name : 'parentId'
		},{
			fieldLabel : '地区等级',
			name : 'levelType'
		},{
			fieldLabel : '城市CODE',
			name : 'cityCode'
		},{
			fieldLabel : '邮编',
			name : 'zipCode'
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '查询',
			id : 'queryZip',
			glyph : 0xf002
		}, {
			xtype : 'button',
			text : '重置',
			id : 'reset',
			glyph : 0xf021
		}, {
			xtype : 'button',
			id : 'cancel',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});