<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link href="image/favicon.ico" rel="shortcut icon" type="image/ico"/>
  	<link href="image/favicon.ico" rel="bookmark" type="image/ico"/>
    <base href="<%=basePath%>">
    
    <title>发布内容</title>
    
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript" src="ueditor/editor_config.js"></script>
<script type="text/javascript" src="ueditor/editor_all.js"></script>
<link rel="stylesheet" href="ueditor/themes/default/ueditor.css">

<script type="text/javascript">
	$(function() {
		
		function checklogin() {
			var username = "<%=session.getAttribute("username")%>";
			if (username=='null') {
				logined.style.display = "none";
				for ( var i = 0; i < editpara.length; i++) {
					editpara[i].style.display = "none";
				}
				loginName.style.display = "none";
			}else{
				notlogin.style.display = "none";
			}

		}
		checklogin();

	});
</script>
  </head>
  
  <body>
  	<div id="header" style="background: #e8e8e8;margin-top:-0.6%;" >
  	<div id="loginName" align="right">欢迎你：${username }<a href="login/exit">退出登录</a></div>
  	</div>
  	<br/><br/>
  	<div id="Nav" style="background: #CAE1FF;">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="login/edittext01">首页</a>&gt;&gt;发表贴子
	</div>
	<br />
	<br />
    <div align="center">你有什么想跟大家说的，写下来吧</div>
    <hr width="1200" />
    <div class="addText">
    <form action="login/edittext"  method="post" name="form1">
    	<table>
    		<tr><td>标题</td><td><input type="text" id="title" name="title"  ></td></tr>
    		<tr><td>段落标题</td><td><input type="text" name="ptitle" size="40px"/></td></tr>    		
    	</table>
    	<script id="editor" name="content"></script>
    	 <script type="text/javascript">
    	var editor = new baidu.editor.ui.Editor();
    	editor.render('editor');
		</script> 
    	<div align="center"><input type="submit" class="BTN" value="提交" /></div>
    </form>
    </div>
    <div id="foot" align="center" style="background: white;">
		<img src="image/szlFooter.png"/>
		<div align="center">© <a href="http://www.szltoy.com/">szltoy.com</a> Corporation.</div>
	</div>
  </body>
</html>
