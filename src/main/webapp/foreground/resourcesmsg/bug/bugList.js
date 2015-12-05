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
	});
	
	/*//查询的公用加载下拉列表
	function selectQuery(url,selectName){
		var data = {"date":new Date()};
		var html = "<option value=\"\">ALL--所有</option>";
		$.getJSON(url,data,function(data){
			$.each(data,function(index,value){
				html = html + "<option value=\"" + value.valueField 
				+ "\">"+ value.valueField + "--" +value.displayField + "</option>";
			});
			$(".queryModal select[name='"+selectName+"']").html(html);
		});
	}

	//添加的公用加载下拉列表
	function selectAdd(url,selectName){
		var data = {"date":new Date()};
		var html = "<option value=\"\">--请选择--</option>";
		$.getJSON(url,data,function(data){
			$.each(data,function(index,value){
				html = html + "<option value=\"" + value.valueField 
				+ "\">"+ value.valueField + "--" +value.displayField + "</option>";
			});
			$(".addModal select[name='"+selectName+"']").html(html);
		});
	}*/