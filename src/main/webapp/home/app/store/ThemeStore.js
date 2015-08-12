Ext.define('AM.store.ThemeStore', {
	extend : 'Ext.data.Store',
	model : 'AM.model.ThemeModel',
	data : [{ 
		value: 'aria', 
		name: 'Aria主题' 
	},{ 
		value: 'neptune', 
		name: 'Neptune主题' 
	},{ 
		value: 'crisp', 
		name: 'Crisp主题' 
	},{ 
		value: 'classic', 
		name: 'Classic主题' 
	},{ 
		value: 'gray', 
		name: 'Gray主题' 
	}]
});