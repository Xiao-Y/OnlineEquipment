$(function(){
	/*//加载查询的下拉列表-----start
		selectQuery("../foreground/resourcesmsg/bug/getSeverity","severity");
		selectQuery("../foreground/resourcesmsg/bug/getBugType","bugType");
		selectQuery("../foreground/resourcesmsg/bug/getSeverity","severity");
		selectQuery("../foreground/resourcesmsg/bug/getReappear","reappear");
		selectQuery("../foreground/resourcesmsg/bug/getStatus","status");
		//加载查询的下拉列表-----end

		//加载添加的下拉列表-----start
		selectAdd("../foreground/resourcesmsg/bug/getSeverity","severity");
		selectAdd("../foreground/resourcesmsg/bug/getBugType","bugType");
		selectAdd("../foreground/resourcesmsg/bug/getSeverity","severity");
		selectAdd("../foreground/resourcesmsg/bug/getReappear","reappear");
		selectAdd("../foreground/resourcesmsg/bug/getStatus","status");
		//加载添加的下拉列表-----end
*/
	//查询BUG
	$(".querySubmit").bind("click",function(){
		var data = $(".queryModal form").serialize();
		var url = "../foreground/resourcesmsg/bug/index";
		$("#content").load(url,data,function(){
			$(".modal-backdrop").remove();
		});
	});
	
	//加载数据列表
	$(".datatable").each(function(){
		$this = $(this);
		var url = $this.attr("url");
		var aoColumns = [{
				"mDataProp": "title",
				"sWidth": "30%"
			},{
				"mDataProp": "status",
				"sWidth": "10%",
				"fnRender": function(data, type, row){
					var str = "";
					if(type == "1"){
						str = '<span class="label label-success">已修改</span>';
					}else if(type == "2"){
						str = '<span class="label label-important">未修改</span>';
					}else{
						str = type;
					}
					return str;
				}
			},{
				"sWidth": "10%",
				"mDataProp": "parentName"
			},{
				"sWidth": "10%",
				"mDataProp": "childrenName"
			},{
				"sWidth": "10%",
				"mDataProp": "severity",
				"fnRender": function(data, type, row){
					var str = "";
					if(type == "5"){
						str = '<span class="label label-info">建议</span>';
					}else if(type == "2"){
						str = '<span class="label">一般</span>';
					}else if(type == "3"){
						str = '<span class="label label-warning">严重</span>';
					}else if(type == "4"){
						str = '<span class="label label-important">致命</span>';
					}else if(type == "1"){
						str = '<span class="label label-success">提示</span>';
					}else{
						str = type;
					}
					return str;
				}
			},{
				"sWidth": "10%",
				"mDataProp": "reappear",
				"fnRender": function(data, type, row){
					var str = "";
					if(type == "1"){
						str = '<span class="label label-info">很难重现</span>';
					}else if(type == "2"){
						str = '<span class="label label-warning">随机重现</span>';
					}else if(type == "3"){
						str = '<span class="label label-important">必然重现</span>';
					}else{
						str = type;
					}
					return str;
				}
			},{
				"sWidth": "10%",
				"mDataProp": "bugType",
				"fnRender": function(data, type, row){
					var str = "";
					if(type == "1"){
						str = '<span class="label">规范问题</span>';
					}else if(type == "2"){
						str = '<span class="label">性能问题</span>';
					}else if(type == "3"){
						str = '<span class="label">安全问题</span>';
					}else if(type == "4"){
						str = '<span class="label">页面问题</span>';
					}else if(type == "5"){
						str = '<span class="label">其它问题</span>';
					}else if(type == "6"){
						str = '<span class="label">功能问题</span>';
					}else{
						str = type;
					}
					return str;
				}
			}];
		var aoColumnDefs = [{ 
				"aTargets": [7],
				"mDataProp" : "id",
				"sWidth": "10%",
				"fnRender" : function(data,type,full){
					var html = "";
					html = '<a class="btn btn-info" href="#"><i class="halflings-icon white edit"></i></a>';
					html = html + '<a class="btn btn-danger" href="#"><i class="halflings-icon white trash"></i></a>';
					return html;
				}
			}];
		initDataTable($this,url,aoColumns,aoColumnDefs);
	});
});
	
	