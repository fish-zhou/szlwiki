package com.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

@Component("exit")
public class ExitAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private UserService userservice;
	@Resource(name="userservice")
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	//�˳���¼
	public String execute()throws Exception{
		boolean login = userservice.exit();
		if(login){
			return SUCCESS;
		}else{
			return LOGIN;
		}
	}
}
