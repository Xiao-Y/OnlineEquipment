Ext.define('AM.view.ContentPanel', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.contentPanel', 
    layout: "fit",
    border:false,
    closable : true,//显示关闭按钮
    headerPosition: 'left',
	glyph:0xf123,
	//glyph:0xf1cd,
    initComponent: function() {
        var me = this;
        me.callParent(arguments);
		me.html = '<iframe src=\'' + me.url + '\' frameborder=\'0\' width=\'100%\' height=\'100%\' />';
    }
});