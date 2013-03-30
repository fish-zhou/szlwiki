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
    
    <title>注册wiki</title>
    <link rel="stylesheet" href="css/reg_css.css" type="text/css" />
    <script type="text/javascript" src='jq/jquery-1.8.0.min.js'></script>
	<script type="text/javascript">
		$(function() {
			function checkinput02(){
				var wrongNum = document.getElementById("wrongNum").value;
				if(wrongNum==='0'){
					emailWrong.style.display="block";
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
                alert('提示\n\n请输入有效的E_mail！');
                myreg.focus();
                return false;
           }
		if(document.form1.name.value===""){
			window.alert("请输入一个名字！");
			document.form1.name.focus();
			return false;
		}
		if(document.form1.password.value===""){
			window.alert("请输入密码！");
			document.form1.password.focus();
			return false;
		}if(document.form1.passwordConfirm.value===""){
			window.alert("请再次输入密码！");
			document.form1.passwordConfirm.focus();
			return false;
		}
		if((document.form1.passwordConfirm.value)!=(document.form1.password.value)){
			window.alert("两次密码不一致，请重新输入！");
			document.form1.passwordConfirm.value="";
			document.form1.password.value="";
			document.form1.password.focus();
			
			return false;
		}
		else{
			document.form1.submit();
	}
  }
	</script>
  </head>
  
  <body>
  <input type=hidden  id=wrongNum  value="${wrongNum}"/>
	<div id="regTop" > 我注册过了，来<a href="login.jsp">登录</a></div> 
  <div id="reg">
  		<div id="regLogo">
  			<img height="180" width="570"src="image/regLogo.png"/>
  		</div>	
		<form action="login/register" method="post" name="form1" >
		<div  id="regInput" class="css_table">
		<div  align="center"><strong>新用户注册</strong></div>
   		 <div > 以下带<span style="color:red;">*</span>的为必输项</div>
   		 <br/>				
			<div class="css_tr">
					<div class="css_td" >注册邮箱：</div>
            		<div class="css_td" >
						<input id="email" class="inputArea" type="text" name="email" />
					</div>
					<div class="css_td" >
						<span  style="color:red;">*	</span><div id=emailWrong style="display:none;color:red;">该邮箱已经被注册过！</div>
          		 	</div>
          		 	<br />
          	</div>
          	<br />
          	
			 <div class="css_tr">
				<div class="css_td" >输入昵称：</div>
	             <div class="css_td" > 
					<input id="name" class="inputArea" type="text" name="username" />
				</div>
				<div class="css_td" >
				<span style="color:red;">*	</span>
				</div>
          	</div>
            <br/>
            	
			<div class="css_tr">
				<div class="css_td" >登录密码：</div>
            	<div class="css_td" >
					<input id="password" class="inputArea" type="password" name="password" />
				</div>
				<div class="css_td" >
				<span style="color:red;">*</span>
				</div>
          	</div>
          	<br/>
          	
			<div class="css_tr">
				<div class="css_td" >密码确认：</div>
            	<div class="css_td" >
					<input id="passwordConfirm" class="inputArea" type="password" name="repassword" />
				</div>
				<div class="css_td" >
				<span style="color:red;">*</span>
				</div>
          	</div> 
          	<br/>
          		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
				 <input type="button" class="loginBTN" name="button2" value="提交" onClick="checkinput()"/>
				&nbsp;&nbsp;<input type="reset" class="cancleBTN" name="button1" value="重填" onClick="clear()"/>
		</div>  		
		</form>			
	</div>	
	<div id="foot" align="center" >
		<div align="center">© <a href="http://www.szltoy.com/">szltoy.com</a> Corporation.</div>
	</div>		    
 </body>
</html>
