<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>编辑-${para.ptitle}</title>
    
	
	<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript" src="ueditor/editor_config.js"></script>
<script type="text/javascript" src="ueditor/editor_all.js"></script>
<link rel="stylesheet" href="ueditor/themes/default/ueditor.css">
<script type="text/javascript" src='jq/jquery-1.8.0.min.js'></script>
	<script type="text/javascript">
	$(function() {
		function checklogin() {
			var username = "<%=session.getAttribute("username")%>";
			if (username=='null') {
				logined.style.display = "none";
				loginName.style.display = "none";
			} else {
				notlogin.style.display = "none";
			}
		}
		checklogin();
	});
		function checkinput(){
			var content = document.getElementById("content").value;			
			if(content===""){
				alert("提交的修改内容不能为空，请输入修改内容");
				return false;
			}else{
				document.form1.submit();
			}
			
		}
	</script>

  </head>
  
  <body >
  <div id="loginName">欢迎你：${username }</div>
  <div id="header">
  	<div class="viewpoint"  align="right" id="logined">
  		<div id="notlogin" <span style="color:red;">你还没有登录！</span> style="display:block;"><a href="login.jsp">登录</a>|<a href="register.jsp">注册</a></div>
		<a style="color:white;" href="login/exit">退出登录</a>
	</div>
  </div>
  <br/><br/><br/>
  <div id="Nav" style="background: #CAE1FF;">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="login/edittext01">首页</a>&gt;&gt;<a href="javascript:history.go(-1)">贴子详情</a>&gt;&gt;修改帖子
	</div>
	<br />
	<br />
	<div id="editparaContent">
  	<form action ="login/editpara01" name="form1" method="post">
    	<h3>修改段落————${para.ptitle}</h3>
   		<hr >    
    	<h4>标题</h4>  	
    	<input type="text" id="ptitle" value="${para.ptitle }" name="ptitle"/>
  	
    	<h4>内容</h4>  	

   		<script type="text/plain" id="editor" name="content">
   	    ${para.content}
		</script> 
   		 <script type="text/javascript">
    	var editor = new baidu.editor.ui.Editor();
    	editor.render('editor');
		</script> 
   	 	<input type=hidden  name=textid value="${textid}"/>
    	<input type=hidden  name=paraid value="${para.paraid}"/>
		<div align="center"><input type ="submit" class="BTN"  value="提交修改" /></div>
	</form>
	</div>
	<div id="foot" align="center" style="background: white;">
		<img src="image/szlFooter.png"/>
		<div align="center">© <a href="http://www.szltoy.com/">szltoy.com</a> Corporation.</div>
	</div>
  </body>
</html>
