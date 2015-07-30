Ext.onReady(function() {
	new Ext.Viewport({
		title : "Viewport",
		layout : "border",
		defaults : {
			bodyStyle : "background-color: #FFFFFF;",
			frame : true
		},
		items : [ {
			region : "west",
			width : 90,
			title : 'north',
			collapsible : true
		}, {
			region : "east",
			width : 90,
			title : 'north',
			collapsible : true
		}, {
			region : "north",
			height : 100,
			title : 'north',
			collapsible : true
		}, {
			region : "center",
			split : true,
			border : true,
			collapsible : true,
			title : 'center'
		}, {
			region : "south",
			title : "south",
			split : true,
			border : true,
			collapsible : true,
			height : 100
		}]
	});
});