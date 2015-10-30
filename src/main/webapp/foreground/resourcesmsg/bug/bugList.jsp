<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/foreground/resource/public.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index">资源管理</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">BUG管理</a></li>
</ul>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>BUG列表
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting" id="queryModal"><span class="label label-info">高级查询</span></a>
				<a href="#" class="btn-setting" id="addModal"><span class="label label-success">添加BUG</span></a>
				<a href="#" class="btn-setting" id="clearModal"><span class="label">重置查询</span></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				<thead>
					<tr>
						<th>BUG标题</th>
						<th>BUG状态</th>
						<th>父模块</th>
						<th>子模块</th>
						<th>严重程度</th>
						<th>重现规律</th>
						<th>BUG类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bugs }" var="bug">
						<tr>
							<input type="hidden" value="${bug.id }" name="id">
							<td class="center">${bug.title }</td>
							<c:choose>
								<c:when test="${bug.status == 1}">
									<td class="center"><span class="label label-success">已修改</span></td>
								</c:when>
								<c:otherwise>
									<td class="center"><span class="label label-important">未修改</span></td>
								</c:otherwise>
							</c:choose>
							<td class="center">${bug.parentName }</td>
							<td class="center">${bug.childrenName }</td>
							<c:choose>
								<c:when test="${bug.severity == 5}">
									<td class="center"><span class="label label-info">建议</span></td>
								</c:when>
								<c:when test="${bug.severity == 2}">
									<td class="center"><span class="label">一般</span></td>
								</c:when>
								<c:when test="${bug.severity == 3}">
									<td class="center"><span class="label label-warning">严重</span></td>
								</c:when>
								<c:when test="${bug.severity == 4}">
									<td class="center"><span class="label label-important">致命</span></td>
								</c:when>
								<c:otherwise>
									<td class="center"><span class="label label-success">提示</span></td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${bug.reappear == 1}">
									<td class="center"><span class="label label-info">很难重现</span></td>
								</c:when>
								<c:when test="${bug.reappear == 2}">
									<td class="center"><span class="label label-warning">随机重现</span></td>
								</c:when>
								<c:otherwise>
									<td class="center"><span class="label label-important">必然重现</span></td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${bug.bugType == 1}">
									<td class="center"><span class="label">规范问题</span></td>
								</c:when>
								<c:when test="${bug.bugType == 2}">
									<td class="center"><span class="label">性能问题</span></td>
								</c:when>
								<c:when test="${bug.bugType == 3}">
									<td class="center"><span class="label">安全问题</span></td>
								</c:when>
								<c:when test="${bug.bugType == 4}">
									<td class="center"><span class="label">页面问题</span></td>
								</c:when>
								<c:when test="${bug.bugType == 6}">
									<td class="center"><span class="label">功能问题</span></td>
								</c:when>
								<c:otherwise>
									<td class="center"><span class="label">其它问题</span></td>
								</c:otherwise>
							</c:choose>
							<td class="center">
								<a class="btn btn-info" href="#"><i class="halflings-icon white edit"></i></a>
								<a class="btn btn-danger" href="#"><i class="halflings-icon white trash"></i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<!-- 高级查询模态框 start -->
<div class="modal hide fade queryModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>高级查询</h3>
	</div>
	<div class="modal-body">
		<div class="box-content">
			<form class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label">BUG标题</label>
						<div class="controls">
 							<input id="title" type="text" name="title">
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG状态</label>
						<div class="controls">
 							<select id="status" name="status">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG出现的父模块</label>
						<div class="controls">
 							<select id="parentId" name="parentId">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG出现的子模块</label>
						<div class="controls">
 							<select id="childrenId" name="childrenId">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">严重程度</label>
						<div class="controls">
 							<select id="severity" name="severity">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">重现规律</label>
						<div class="controls">
 							<select id="reappear" name="reappear">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG类型</label>
						<div class="controls">
 							<select id="bugType" name="bugType">
							</select>
						</div>
 					</div>
 				</fieldset>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-primary querySubmit">查询</a>
		<a href="#" class="btn" data-dismiss="modal">关闭</a>
	</div>
</div>
<!-- 高级查询模态框 end -->

<!-- 添加模态框 start -->
<div class="modal hide fade addModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>高级查询</h3>
	</div>
	<div class="modal-body">
		<div class="box-content">
			<form class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label">BUG标题</label>
						<div class="controls">
 							<input id="title" type="text" name="title">
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG状态</label>
						<div class="controls">
 							<select id="status" name="status">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG出现的父模块</label>
						<div class="controls">
 							<select id="parentId" name="parentId">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG出现的子模块</label>
						<div class="controls">
 							<select id="childrenId" name="childrenId">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">严重程度</label>
						<div class="controls">
 							<select id="severity" name="severity">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">重现规律</label>
						<div class="controls">
 							<select id="reappear" name="reappear">
							</select>
						</div>
 					</div>
 					<div class="control-group">
						<label class="control-label">BUG类型</label>
						<div class="controls">
 							<select id="bugType" name="bugType">
							</select>
						</div>
 					</div>
 				</fieldset>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-primary">保存</a>
		<a href="#" class="btn" data-dismiss="modal">关闭</a>
	</div>
</div>
<!-- 添加模态框 end -->
<script type="text/javascript">
	$(function(){
		//加载查询的下拉列表-----start
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

		//查询BUG
		$(".querySubmit").bind("click",function(){
			var data = $(".queryModal form").serialize();
			var url = "../foreground/resourcesmsg/bug/index";
			$("#content").load(url,data,function(){
				$(".modal-backdrop").remove();
			});
		});
	});
	
	//查询的公用加载下拉列表
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
	}
</script>