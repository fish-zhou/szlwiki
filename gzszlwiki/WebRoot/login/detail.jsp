<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <link href="image/favicon.ico" rel="shortcut icon" type="image/ico"/>
  <link href="image/favicon.ico" rel="bookmark" type="image/ico"/>
<base href="<%=basePath%>">

<title>${text.title}</title>

<link rel="stylesheet" href="css/main.css" type="text/css" />

<script type="text/javascript" src='jq/jquery-1.8.0.min.js'></script>
<script type="text/javascript" src='js/detail.js'></script>
<script type="text/javascript" src="ueditor/editor_config.js"></script>
<script type="text/javascript" src="ueditor/editor_all.js"></script>
<link rel="stylesheet" href="ueditor/themes/default/ueditor.css">
<link href="ueditor/third-party/SyntaxHighlighter/shCoreDefault.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="ueditor/third-party/SyntaxHighlighter/shCore.js"></script>  
<script type="text/javascript">      
SyntaxHighlighter.all();       
</script>
<script type="text/javascript">
	$(function() {
		
		function checklogin() {
			var username = "<%=session.getAttribute("username")%>";
			var editpara = $(".button");
			if (username=='null') {
				logined.style.display = "none";
				for ( var i = 0; i < editpara.length; i++) {
					editpara[i].style.display = "none";
				}
				addParaBtn.style.display = "none";
				loginName.style.display = "none";
			} else {
				notlogin.style.display = "none";
			}

		}
		checklogin();
		var status = $(".status");
		var alist = $(".button");
		for ( var i = 0; i < alist.length; i++) {
			alist[i].onclick = function(i) {
				return function() {
					if (status[i].value != "1") {
						alert("这个段落正在被别人修改");
						return false;
					}
				};
			}(i);
		}

	});
</script>
</head>

<body>
	<div id="header" style="background: #e8e8e8;margin-top:-0.6%;" >
  		<div id="logined" align="right" >欢迎你：${username}|<a  href="login/exit">退出登录</a>
  		</div>
		<div  id="notlogin" align="right" >
		<span style="color:red;">你还没有登录！</span>
		<a href="login.jsp">登录</a>|<a
			href="register.jsp">注册</a>
		</div>
	</div>
	
	<div id="detailcontent">
	<h1 style="font-weight:bold;" align="center">${text.title} </h1>
	<div align="right">${text.username}</div>
	<div align="right">${text.lasttime}</div>
	<div id="Nav" style="background: #CAE1FF;">
				<img src="image/ico_site.jpg"  id="ico_site"/>
				网站路径：<a href="login/edittext01">首页</a>&gt;&gt;贴子详情
	</div>
	<br />
	<br />
	
	<div id="detailJsp">
		<c:forEach items="${paras}" var="p">
		<div id="left_avatar"><img src=""/></div>
		<div id="right_paraDtail">
			<div id="detailJspUnit" >
		<hr align="left" width ="200" size="10" color="#AAAFFF" />
			<h2 id="ptitle"><strong> &nbsp;&nbsp;&nbsp;&nbsp;${p.ptitle}</strong></h2>&nbsp;&nbsp;&nbsp;&nbsp;
			<br />
             <p id="pcontent"> ${p.content}</p>
             <p class="buttonLoca"><a class="button" href="login/editpara?paraid=${p.paraid}&textid=${text.textid}">编辑文本</a></p>	
             <br />
             <br />			
			 <br />
			<INPUT TYPE="hidden" class="status" VALUE=${p.status }>
		</div>
		</div>
		 <br />			
		 <br />
		</c:forEach>
	</div>

	<form action="login/addPara" method="post">
	<INPUT TYPE="hidden" class="status" name="title" VALUE="${text.title }">
	<INPUT TYPE="hidden" class="status" name="textid" VALUE="${text.textid}">
	<div align="center" id="addParaBtn"><input id="addPara" class="BTN" type="button" value="增加文本"></div>
	<div id='addParaDiv' name="content"></div>
	</form>
	<div id="foot" align="center" style="background: white;">
		<img src="image/szlFooter.png"/>
		<div align="center">© <a href="http://www.szltoy.com/">szltoy.com</a> Corporation.</div>
	</div>
	</div>
</body>
</html>
