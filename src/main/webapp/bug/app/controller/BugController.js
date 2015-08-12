Ext.define('AM.controller.BugController', {
	extend : 'Ext.app.Controller',
	views : [ 'BugList'],//, 'BugAdd', 'BugQuery' 
	stores : [ 'BugStore'],
	models : [ 'BugModel' ],
	init : function() {
		this.control({
			
		});
	}
});