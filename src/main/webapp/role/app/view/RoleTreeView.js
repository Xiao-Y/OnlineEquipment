Ext.define('AM.view.RoleTreeView', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.roleTree',
	border : false,
	// 是否显示根节点
	rootVisible : false,
	// 数据集
	store : 'RoleTreeStore'
});