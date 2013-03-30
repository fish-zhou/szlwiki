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

@Component("login")
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private UserService userservice;
	
	TextService textservice ;
	@Resource(name="textservice")
	public void setTextservice(TextService textservice) {
		this.textservice = textservice;
	}
	@Resource(name="userservice")
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	
	int login ;
	
	//execute 锟斤拷锟斤拷锟斤拷锟斤拷锟铰硷拷锟斤拷锟�
	public String execute()throws Exception{
		//鍚庡彴杈撳叆鍒ゆ柇
		if(email!=null&&!email.equals("")&&password!=null&&!password.equals("")){
			login = userservice.login(this.getEmail(),this.getPassword());
			
			if(login==2){
				ActionContext actioncontext = ActionContext.getContext();
				Map session = actioncontext.getSession();
				//锟斤拷锟戒、锟斤拷锟诫都锟斤拷确锟斤拷锟斤拷录锟缴癸拷锟斤拷锟斤拷锟矫伙拷锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷session锟斤拷
				String [] nameAndRole = userservice.getUsernameAndRole(email);
				String username = nameAndRole[0];
				String role = nameAndRole[1];
				
				String avatar_sml = userservice.getAvatar_sml(username);
				if("".equals(avatar_sml)){
					avatar_sml = "/image/user/defaultAvatar_sml.jpg";
				}
				
				session.put("LOGIN", true);
				session.put("username", username);
				session.put("avatar_sml", avatar_sml);
				session.put("email", email);
				session.put("role", role);
				//System.out.println("username"+username);
				return SUCCESS;
			}else if(login == 1){
				ActionContext actionContext = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
				request.setAttribute("wrongNum", 1);
				return LOGIN;
			}else{
				ActionContext actionContext = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
				request.setAttribute("wrongNum", 0);
				return LOGIN;
			}
		}else{
			return LOGIN;
		}	
				
	}
//setters and getters
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
