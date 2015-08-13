//通过父id查询出其下的所有子集合
Ext.define('AM.store.ChildrenMenuStore', {
	extend : 'Ext.data.Store',
	model : 'AM.model.MenuModel',
	proxy : {
		url : '../bug/parentMenuList',
		type : 'ajax',
		reader : {
			type : 'json',
			rootProperty : 'root'
		}
	}
});