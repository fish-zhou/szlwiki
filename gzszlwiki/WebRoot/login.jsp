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
    
    <title>欢迎登录wiki</title>
	
	<link rel="stylesheet" href="css/login_css.css" type="text/css" />
	<script type="text/javascript" src='jq/jquery-1.8.0.min.js'></script>
	<script type="text/javascript">
		$(function() {
			function checkinput02(){
				var wrongNum = document.getElementById("wrongNum").value;
				if(wrongNum==='0'){
					emailWrong.style.display="block";
				}
				if(wrongNum==='1'){
					pswWrong.style.display="block";
				}
			}
			checkinput02();
		});
		function checkinput(){
		var temp = document.getElementById("email").value;
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				
		if(document.form1.email.value===""){
			window.alert("请输入邮箱地址！");
			document.form1.email.focus();
			return false;
		}if(!myreg.test(temp))
           {
                alert('\n请输入有效的E_mail！');
                myreg.focus();
                return false;
           }
		if(document.form1.password.value===""){
			window.alert("请输入密码！");
			document.form1.password.focus();
			return false;
		}else{ 
			document.form1.submit();
	}
  }
	</script>
  </head>
  
  <body>
  	<input type=hidden  id=wrongNum  value="${wrongNum}"/>
  
    <div id="login" >
   		 <div id="loginLogo">
			<img height="180" width="370"src="image/LoginWIKI.png"/>
  		</div>
  		
        <div id="loginInput" class="css_table">
		<div  align="center"><strong> 员工登录</strong></div>
		*请使用三之乐公司邮箱登录，默认密码为123，首次登录后请修改密码(这个功能正在进行中)
		<form action="login.action" method="post" name="form1">			
			<br />
			<div class="css_tr">
				<div class="css_td" >公司邮箱&nbsp;</div>
	            <div class="css_td" >
	            	<input type="text" id="email"  name="email"  style= "width:200px;height:35px " maxlength="25">
	            </div>							
				<div class="css_td" id=emailWrong style="display:none;color:red;">邮箱不存在！</div>	
			</div>		         	
			<br />
			
			<div class="css_tr">
				<div class="css_td" >密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;</div>
				<div class="css_td" >
					<input type="password" id="password"  name="password" style= "width:200px;height:35px "  value="" maxlength="25">
				</div>
	            <div class="css_td" >
	            	<div id=pswWrong style="display:none;color:red;">密码错误！</div>
	            </div>	
			</div>	
			<br />
			
			<div class="css_tr">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="css_td" >
				<input type="button" class="loginBTN" value="登陆" onClick="checkinput()">
				&nbsp;&nbsp;<input type="reset" class="cancleBTN"  value="重填" onClick="clear()">
				</div>																			
        	</div>
        	<br />
        	&nbsp;&nbsp;
        	<div style="background:White;width:80px;"><a href="register.jsp"></a></div>
		</form>
	  </div>	
		
	</div>
	<div id="foot" align="center" >
		<div align="center">© <a href="http://www.szltoy.com/">szltoy.com</a> Corporation.</div>
	</div>
  </body>
</html>
