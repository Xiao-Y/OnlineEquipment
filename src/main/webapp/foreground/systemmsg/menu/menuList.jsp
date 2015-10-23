<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/foreground/resource/public.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index">系统管理</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">菜单管理</a></li>
</ul>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>菜单列表
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting"><span class="label label-info">高级查询</span></a>
				<a href="#" class="btn-setting"><span class="label label-info">添加菜单</span></a>
				<a href="#" class="btn-setting"><span class="label label-info">批量删除</span></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				<thead>
					<tr>
						<th><input type="checkbox"></th>
						<th>菜单名称</th>
						<th>请求路径</th>
						<th>所在位置</th>
						<th>父级菜单名称</th>
						<th>节点类型</th>
						<th>创建时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menus }" var="menu">
						<tr>
							<input type="hidden" value="${menu.id }" name="id">
							<input type="hidden" value="${menu.parentId }" name="parentId">
							<input type="hidden" value="${menu.menuType }" name="menuType">
							<td><input type="checkbox"></td>
							<td>${menu.menuName }</td>
							<td class="center">${menu.menuUrl }</td>
							<td class="center">${menu.seq }</td>
							<td class="center">${menu.parentName }</td>
							<c:choose>
								<c:when test="${menu.menuType == 0 }">
									<td class="center"><span class="label label-warning">${menu.menuTypeName }</span></td>
								</c:when>
								<c:otherwise>
									<td class="center"><span class="label label-info">${menu.menuTypeName }</span></td>
								</c:otherwise>
							</c:choose>
							<td class="center">${menu.createTime }</td>
							<td class="center">${menu.updateTime }</td>
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