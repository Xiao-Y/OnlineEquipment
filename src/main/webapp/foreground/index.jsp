<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/foreground/resource/public.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- start: Meta -->
		<title>设备故障申报系统</title>
		<meta name="description" content="设备故障申报系统">
		<meta name="author" content="XiaoY">
		<meta name="keyword" content="XiaoY,Billow">
		<!-- end: Meta -->
		
		<!-- start: Mobile Specific -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- end: Mobile Specific -->
		
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<link id="ie-style" href="resource/css/ie.css" rel="stylesheet">
		<![endif]-->
		
		<!--[if IE 9]>
			<link id="ie9style" href="resource/css/ie9.css" rel="stylesheet">
		<![endif]-->
			
		<!-- start: Favicon -->
		<link rel="shortcut icon" href="resource/img/favicon.ico">
		<!-- end: Favicon -->
	</head>
	<body>
		<!-- start: Header -->
		<jsp:include page="/foreground/home/header.jsp"/>
		<!-- start: Header -->
		
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Main Menu -->
			<jsp:include page="/foreground/home/left.jsp"/>
			<!-- end: Main Menu -->
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			<!-- start: Content -->
			<div id="content" class="span10">
				<jsp:include page="/foreground/home/content.jsp"/>
			</div><!--/.fluid-container-->
			<!-- end: Content -->
		</div><!--/#content.span10-->
	</div><!--/fluid-row-->
	<div class="clearfix"></div>
	<!-- 底部 -->
	<footer>
		<p>
			<span style="text-align:left;float:left">&copy; 2015 
				<a href="https://github.com/Xiao-Y/OnlineEquipment" alt="OnlineEquipment 设备故障申报系统">OnlineEquipment 设备故障申报系统</a>
			</span>
		</p>
	</footer>
	<!-- start: JavaScript-->
	<script type="text/javascript">
		function clickLoad(url){
			if(url != ''){
				$.ajax({
					url : url,//url
					async : true,//异步
					type : "POST",//请求方式
					dataType : "html",//返回数据格式
					success : function(data, textStatus){//请求成功
						$("#content").html(data);
					},
					error : function(){//请求错误
						$("#content").html("数据显示错误，请重试！");
					}
				});
			}
		}
	</script>
	<!-- end: JavaScript-->
	</body>
</html>