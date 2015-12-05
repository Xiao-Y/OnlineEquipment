<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="sidebar-left" class="span2">
	<div class="nav-collapse sidebar-nav">
		<ul class="nav nav-tabs nav-stacked main-menu">
		<!-- 
			<li onclick="clickLoad('index')"><a href="javascript:void(0)"><i class="icon-home"></i><span class="hidden-tablet">首页</span></a></li>
		 -->
			<c:forEach items="${trees }" var="tree" varStatus="num">
				<li>
					<a class="dropmenu" href="#">
						<i class="icon-folder-close-alt"></i>
						<span class="hidden-tablet"> ${tree.text}</span>
						<span class="label label-important">${fn:length(tree.children) }</span>
					</a>
					<c:if test="${!empty tree.children }">
						<ul>
							<c:forEach items="${tree.children }" var="children" varStatus="numb">
								<li onclick="clickLoad('${children.url }')" style="background: #3A3A3A">
									<a class="submenu" href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<i class="icon-file-alt"></i>
										<span class="hidden-tablet">${children.text }</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</li>
			</c:forEach>
			<li><a href="${pageContext.request.contextPath }/icon.html"><i class="icon-star"></i><span class="hidden-tablet">图标icons</span></a></li>
			<li><a href="${pageContext.request.contextPath }/login.html"><i class="icon-lock"></i><span class="hidden-tablet">重新登陆</span></a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$('.dropmenu').click(function(e){
		$this = $(this);
		e.preventDefault();
		$(this).parent().find('ul').slideToggle();
		
		removeClasee();
		$this.parent().addClass('active');
	});
	$(".submenu").click(function(){
		$this = $(this);
		removeClasee();
		$this.parent().parent().parent().addClass('active');
		$this.parent().addClass('active');
	});
	
	function removeClasee(){
		$('ul.main-menu li a').each(function(){
			$(this).parent().removeClass("active");
		});
		$('ul.main-menu li ul li a').each(function(){
			$(this).parent().removeClass("active");
		});
	}
});
</script>