package com.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.TextService;
import com.service.UserService;

@Component("register")
public class RegAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String email ;
	private String username ;
	private String password ;
	private String repassword ;
	private UserService userservice;
	private TextService textservice;
	
	@Resource(name="textservice")
	public void setTextservice(TextService textservice) {
		this.textservice = textservice;
	}
	@Resource(name="userservice")
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	boolean register =false;
	//execute ��������ע������
	public String execute()throws Exception{
		//后台输入信息验证
		if(email.equals("")||email==null||username.equals("")||username==null||
				password.equals("")||password==null||repassword.equals("")||repassword==null){
			return LOGIN;
		}else{
			register = userservice.register(email,username,password);
			if(register==true){
				ActionContext actioncontext = ActionContext.getContext();
				Map session = actioncontext.getSession();
				session.put("username", username);
				session.put("role", 1);
				return SUCCESS;
			}else{
				ActionContext actionContext = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
				request.setAttribute("wrongNum", 0);
				return LOGIN;
			}	
		}
		
	}
	
	
}
