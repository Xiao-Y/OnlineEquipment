Ext.define('AM.view.MenuTreeView', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.menutree',
	border : false,
	// 规定锚链接地址的显示区域
	hrefTarget : 'mainContent',
//	defaults : { // defaults 将会应用所有的子组件上,而不是父容器
//		autoScroll : true
//	},
	// 是否显示根节点
	rootVisible : false,
	// 数据集
	store : 'MenuTreeStore'
		// ,
		// // 菜单样式
		// bodyStyle : {
		// background : '#ffc',
		// padding : '10px'
		// }
	});