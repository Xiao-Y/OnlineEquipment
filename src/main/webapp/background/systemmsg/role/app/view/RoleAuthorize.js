Ext.define('AM.view.RoleAuthorize', {
	extend : 'Ext.window.Window',
	id : 'authorizeWindow',
	alias : "widget.authorizeSave",
	height : 400,
	width : 300,
	layout : 'fit',
	modal:true,//模态框
	items : [ {
		xtype : 'form',//为了显示滑动条
		autoScroll:true,
		items:[{
			xtype : 'treepanel',//添加菜单树
			id : "treepanelId",
			border : false,
			rootVisible : false,// 是否显示根节点
			store : 'RoleTreeStore',
			listeners : {//添加监听 设置树的节点选择的级联关系
 				"checkchange": function(node, checked) {
      				listenerCheck(node, checked);
  				}
			}
		}]
	} ],
	dockedItems : [ {
		xtype : 'toolbar',
		dock : 'bottom',
		items : [ '->', {
			xtype : 'button',
			text : '保存',
			id : 'saveAuthorize',
			glyph : 0xf002
		}, {
			xtype : 'button',
			id : 'cancelAuthorize',
			text : '关闭',
			glyph:0xf00d
		} ]
	} ]
});

//添加监听 设置树的节点选择的级联关系
var listenerCheck = function(node, checked) {
	childHasChecked(node,checked);
	var parentNode = node.parentNode;
	console.info(parentNode);
 	if(parentNode != null) {   
  		parentCheck(parentNode,checked);   
 	} 
};
 //级联选中父节点
var parentCheck = function(node ,checked){    
  	var childNodes = node.childNodes;
	for (var i = 0; i < childNodes.length; i++) {
		if (childNodes[i].get('checked')) {
			node.set('checked',checked);
			continue;
		}else{
			node.set('checked',false);
			break;
		}
	}
	var parentNode = node.parentNode;
	if (parentNode != null ) {
		parentCheck(parentNode,checked);
  	}
};
 //级联选择子节点
var childHasChecked = function (node, checked) {
 	node.cascadeBy(function (child) {
    	child.set("checked",checked)
 	});
};