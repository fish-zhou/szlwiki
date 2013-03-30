<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>wikiPageHead</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
	<div id="logalleft">
		<div id="logalright">
		<s:if test="#session.LOGIN">欢迎你：<s:property value="#session.username"/>已登录|
		<a href="login/exit" target="_parent">退出</a>
		</s:if>
	 	<s:else>
	 	<a href="/register.jsp" target="_parent">[注&nbsp;册]</a> | 
	 	<a href="/login.jsp" target="_parent">会员登录</a>
	 	</s:else>
		</div>
	</div>
  </body>
</html>
