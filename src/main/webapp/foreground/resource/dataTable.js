/**
 * 初始化列表
 * @param {} element 列表添加到那个tablegh 
 * @param {} url 行程请求的url
 * @param {} aoColumns 列表的列
 * @param {} aoColumnDefs 不显显示的列
 */
function initDataTable(element,url,aoColumns,aoColumnDefs){
		
	element.dataTable({
		//"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
		"serverSide": true,//页面在加载时就请求后台
		"sAjaxSource" : url,//"../foreground/resourcesmsg/bug/getBugList",
		"aoColumns" : aoColumns,
		"aoColumnDefs": aoColumnDefs,//隐藏列
		"iDisplayLength": 10,//指定页面大小
		"sServerMethod": "POST",
		"oLanguage": {
			"sProcessing": "正在加载中......",
			"sLengthMenu": "每页显示 _MENU_ 条记录",
			"sZeroRecords": "没有查询到数据！",
			"sEmptyTable": "表中无数据存在！",
			"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmpty": "显示0到0条记录",
			"sInfoFiltered": "数据表中共为 _MAX_ 条记录",
			"sSearch": "搜索",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上一页",
				"sNext": "下一页",
				"sLast": "末页"
			}
		},
		"fnDrawCallback": function(oSettings) {
         	element.$("td").css("text-align", "center");  
         },//重绘回调函数
		"bPaginate": true, //翻页功能
		"bLengthChange": false, //改变每页显示数据数量
		"bPaginate": true, //是否显示（使用）分页器
		"sPaginationType": "bootstrap",//用于指定分页器风格
		"bAutoWidth" : true,
		"bFilter": false, //是否显示搜索
		"bProcessing": true,//是否显示“正在处理”这个提示信息
		"bScrollInfinite": false,//以指定是否无限滚动（与sScrollY配合使用），在大数据量的时候很有用。当这个标志为true的时候，分页器就默认关闭
		"bInfo": true, //是否打印信息
		"bDestroy": true//用于当要在同一个元素上执行新的dataTable绑定时，将之前的那个数据对象清除掉，换以新的对象设置
	});
}
