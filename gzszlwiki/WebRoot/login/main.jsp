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
<link href="image/favicon.ico" rel="shortcut icon" type="image/ico" />
<link href="image/favicon.ico" rel="bookmark" type="image/ico" />
<base href="<%=basePath%>">

<title>三之乐wiki</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript" src='jq/jquery-1.8.0.min.js'></script>

</head>

<body>
	<div id="header" style="background: #e8e8e8;height: 35px;margin: auto;">
		<div id="logined" align="right">
			<div id="exsitDiv" style="float: right;">
				<a href="login/exit">[退出登录]</a>
			</div>
			<div id="avatar" style="float: right;">
				<a href="login/userInf"><img src="<%=path%>/${avatar_sml}"
					alt="编辑个人信息" />
				</a>&nbsp;|&nbsp;
			</div>
			<div id="loginedInf" style="float: right;">欢迎你：${username}</div>
		</div>
		<div id="notlogin" align="right">
			<span style="color:red;">你还没有登录！</span> <a href="login.jsp">登录</a>|<a
				href="register.jsp">注册</a>
		</div>
	</div>

	<div id="mid">
		<div id="logo" style="padding-left:6em;">
			<img src="image/sanzhileWIKI_main.png" width="415" alt="三之乐的wiki" />
		</div>
		<div id="mid_content"></div>
	</div>
	<div id="notes" style="border:1px solid #785;">
		<p>提示:</p>
		<ul>
			<li>1.wiki目前还有一些没能解决的bug，如果出现回到首页没有数据，或者执行操作出现500错误时，请刷新页面解决（刷新一次不行？再刷新，再刷新···，还是不行，请QQ联系周宇反馈，谢谢）。</li>
			<li>2.编辑器上传图片和附件目前只能上传几K大小，所以基本不能用，这个正在解决中···大家暂时不要用。</li>
			<p>周宇</p>
		</ul>
	</div>
	<div class="buttonLoca" align="right" id="addtext"
		style="display:block;">
		<a class="button" href="login/addText.jsp">发布新内容</a>
	</div>
	<br />
	<br />
	<div id="maincontent" align="center">
		<ul>
			<c:forEach items="${texts}" var="t">
				<li>
					<div id="textUnit" style="border-bottom: 1px dashed #ccc">
						<div id="left_avatar"><img src=""/></div>
						<div id="text_area">
							<div>
								<a style="color: #00A;font-size: 45px;"
									href="login/viewtext?textid=${t.textid }">${t.title }</a>
							</div>
							<div>
								${t.username }<img
									src="<%=path%>/image/user/defaultAvatar_mid.jpg" />&nbsp;&nbsp;发表于：${t.createTime
								}&nbsp;&nbsp;浏览：${t.readTimes }次
							</div>
							<div>最后修改人：${t.lastEditor }&nbsp;&nbsp;时间：${t.lasttime }</div>
							<div>
								<div class="css_td">
									<a id="deletebutton" class="deletetext" style="display:none;"
										href="login/deleteText?textid=${t.textid }">删除</a>
								</div>
								<br />
							</div>
						</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<div id="pageNav" style="background: #B7B8CA;">
		<c:choose>
			<c:when test="${page eq 1}">
  			页数：${page}/${totalPage}<a
					href="<%=path%>/login/edittext01?page=${page+1}&pagesize=${pagesize}">下一页</a>
			</c:when>
			<c:when test="${!hasNextPage && page>1}">
  			页数：${page}/${totalPage}<a
					href="<%=path%>/login/edittext01?page=${page+1}&pagesize=${pagesize}">上一页</a>
			</c:when>
		</c:choose>
	</div>
	<div id="foot" align="center" style="background: white;">
		<img src="image/szlFooter.png" />
		<div align="center">
			© <a href="http://www.szltoy.com/" target="_blank" title="访问公司主页">szltoy.com</a>
			Corporation.
		</div>
	</div>
	<script type='text/javascript'>
    	$(function(){
    	
    	function checklogin(){
			var username = "<%=session.getAttribute("username")%>";
    		var deletetext = $(".deletetext");
    		var role = "<%=session.getAttribute("role")%>";

				if (username == 'null') {
					logined.style.display = "none";
					addtext.style.display = "none";
				} else {
					notlogin.style.display = "none";
				}

				if (role === "2") {
					for ( var i = 0; i < deletetext.length; i++)
						deletetext[i].style.display = "block";
				}
			}
			checklogin();
		});
	</script>
</body>

</html>
