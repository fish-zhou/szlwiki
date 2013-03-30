package com.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

@Component("userInf")
public class UserInfAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private UserService userservice;
	@Resource(name="userservice")
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	
	private String oldPassword ;
	private String newPassword ;
	
	/**获取用户信息
	 * */	
	public String getUserInf(){
		ActionContext actioncontext = ActionContext.getContext();
		Map session = actioncontext.getSession();
		String email = (String)session.get("email");
		
		User user = userservice.getUserInf(email);
		if(null!=user){
			HttpServletRequest request = (HttpServletRequest)actioncontext.get(ServletActionContext.HTTP_REQUEST);
			request.setAttribute("user", user);
			return SUCCESS;
		}else{
			return LOGIN;
		}
	}
	
	/**修改用户密码
	 * */
	public String changPsw(){
		ActionContext actioncontext = ActionContext.getContext();
		Map session = actioncontext.getSession();
		String email = (String)session.get("email");
		boolean isOldPwdr = userservice.checkPassword(email, oldPassword);
		if(isOldPwdr){
			boolean isSuccess = userservice.changPsw(email, newPassword);
			if(isSuccess){
				return SUCCESS;
			}else{
				//数据库操作出错
				int code = 9;
				HttpServletRequest request = (HttpServletRequest)actioncontext.get(ServletActionContext.HTTP_REQUEST);
				request.setAttribute("code", code);
				return LOGIN;
			}
		}else{
			//原密码错误
			int code = 1;
			HttpServletRequest request = (HttpServletRequest)actioncontext.get(ServletActionContext.HTTP_REQUEST);
			request.setAttribute("code", code);
			return LOGIN;
		}
		
	}
	
//setters and getters
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
