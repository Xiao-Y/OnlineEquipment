Ext.define('AM.view.ContentPanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.contentPanel', 
    height: 385,
    width: 675,
    layout: {
        type: 'fit'
    },
    headerPosition: 'left',
    title: '',

    initComponent: function() {
        var me = this;
        me.callParent(arguments);
		me.html = '<iframe src=\'' + me.url
		+ '\' frameborder=\'0\' width=\'100%\' height=\'100%\' />';
    }
});