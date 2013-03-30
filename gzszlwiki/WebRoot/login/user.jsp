<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${username}的个人资料</title>
	<script type="text/javascript" src='<%=path%>/jq/jquery-1.8.0.min.js'></script>
	<script type="text/javascript" src='<%=path%>/js/chpwd.js'></script>
  </head>
  
  <body>
  <div id="header" style="background: #e8e8e8;margin-top:-0.6%;">
  		<div id="logined" align="right" >欢迎你：${username}|<a  href="<%=path%>/login/exit">退出登录</a>
  		</div>
  </div>
  
  <div id="Nav" style="background: #CAE1FF;">
				<img src="<%=path%>/image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="<%=path%>/login/edittext01">首页</a>&gt;${username}的个人资料
	</div>
	<br />
	
  <div id="basicMess" align="center">
  	<h1 style="background: #cd4900;color:white;">员工基本资料</h1>
  	<div id="mess">
  	<div id="user_info">
  		<div id="headImg"><img src="<%=path%>/image/user/DefaultImg.jpg"></div>
  		<span><a href="user-face.jsp">更改头像</a></span>
  	</div>
  	<div>公司邮箱：${user.email}</div>
  	<div>姓名：${user.username}</div>
  	<c:choose>
  		<c:when test="${user.gender eq 2}">
  			<div>性别:女</div>
  		</c:when>
  		<c:otherwise>
  		<div>性别:男</div>
  		</c:otherwise>
  	</c:choose>
  	<div>部门：暂无</div>
  	<form action="chpwd" method="post">
  	<div><input id="chpwd" class="BTN" type="button" value="修改登录密码"></div>
  	<div id="chpwdDiv"></div>
  	</form>
  	</div>
  </div>
  
  </body>
</html>
