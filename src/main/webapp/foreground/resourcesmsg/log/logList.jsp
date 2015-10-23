<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/foreground/resource/public.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index">资源管理</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">日志管理</a></li>
</ul>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>日志列表
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting"><span class="label label-info">高级查询</span></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				<thead>
					<tr>
						<th>操作</th>
						<th>用户名</th>
						<th>IP地址</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${logs }" var="log">
						<tr>
							<input type="hidden" value="${log.id }" name="id">
							<td class="center">${log.operation }</td>
							<td class="center">${log.userId }</td>
							<td class="center">${log.ipAddr }</td>
							<td class="center">${log.createTime }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<div class="modal hide fade" id="myModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>Settings</h3>
	</div>
	<div class="modal-body">
		<p>Here settings can be configured...</p>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">关闭</a>
		<a href="#" class="btn btn-primary">保存</a>
	</div>
</div>