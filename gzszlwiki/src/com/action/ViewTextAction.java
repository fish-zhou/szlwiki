package com.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.entity.Paragraph;
import com.entity.Text;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.TextService;
import com.service.UserService;

@Component("viewtext")
public class ViewTextAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private int textid ;
	
	TextService textservice ;
	@Resource(name="textservice")
	public void setTextservice(TextService textservice) {
		this.textservice = textservice;
	}
	private UserService userservice;
	@Resource(name="userservice")
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public int getTextid() {
		return textid;
	}
	public void setTextid(int textid) {
		this.textid = textid;
	}
	
	//查看“文章”详情页面
	public String execute()throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
		
		Text text = textservice.viewText(textid);
		//记录阅读次数
		text.setReadTimes(text.getReadTimes()+1);
		textservice.updateText(text);
		
		Set<Paragraph> paras = text.getParagraphs();
		Paragraph para =null;
		List<String> avatarList = new ArrayList<String>();
		String creater ="";
		Iterator it = paras.iterator();
		while(it.hasNext()){
			para = (Paragraph)it.next();
			creater = para.getCreater();
			String avatar_big = userservice.getAvatar_big(creater);
			avatarList.add(avatar_big);
		}
		request.setAttribute("text", text);
		request.setAttribute("paras", paras);
		request.setAttribute("avatarList", avatarList);
		
		return SUCCESS;
	}
}
