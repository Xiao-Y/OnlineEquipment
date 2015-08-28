Ext.define('AM.view.RoleTreeView', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.roleTree',
	id : "roleTreeView",
	border : false,
	// 是否显示根节点
	rootVisible : true,
	// 数据集
	store : 'RoleTreeStore'
});