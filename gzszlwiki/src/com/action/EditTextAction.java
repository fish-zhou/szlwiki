package com.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import util.PageBean;

import com.constant.Constants;
import com.entity.Paragraph;
import com.entity.Text;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.TextService;
import com.service.UserService;


@Component("edittext")
public class EditTextAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String title ;
	private String ptitle ;
	private String content ;
	private int textid;
	private int page ;//请求第几页
	private int pageSize ;//一页的大小
	
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
	
	//增加文章(addtext)
	public String execute()throws Exception{
		if((!title.equals("")&&title!=null)&&(content!=null)&&!content.equals("")){
			//锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷
			ActionContext actioncontext = ActionContext.getContext();
			Map session = actioncontext.getSession();
			String username = (String)session.get("username");
			
			String title = this.getTitle().trim();
			String content = this.getContent().trim();
			
			Text text = new Text();
			Paragraph para = new Paragraph();
			text.setTitle(title);		
			text.setUsername(username);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			text.setCreateTime(timestamp);
				
			para.setPtitle(this.getPtitle());
			para.setContent(content);
			para.setCreater(username);
			para.setCreateTime(timestamp);
			para.setLastEditor(username);
			para.setLasttime(timestamp);
			
			//默锟斤拷锟睫革拷状态为1锟斤拷锟斤拷锟斤拷锟斤拷锟睫革拷
			para.setStatus(1);
			para.setPorder("a");
			
			Set<Paragraph> paras = new HashSet<Paragraph>();
			paras.add(para);
			text.setParagraphs(paras);
			boolean addtext = textservice.addText(text);
							
			if(addtext){
				return SUCCESS;
			}else{
				return LOGIN;
				}
		}else{			
			return LOGIN;
		}
		
	}
	
	//wiki首页
	public String viewAllText(){
		ActionContext actionContext = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
		
		PageBean pageBean = new PageBean(page,pageSize);
		
		pageBean = textservice.viewText(pageBean);
		
		//取出关联的用户图像
		Text text = null;
		List<String> avatarList = new ArrayList<String>();
		int size = pageBean.getList().size();
		for(int i=0;i<size;i++){
			text = (Text)pageBean.getList().get(i);
			String userName = text.getUsername();
			String avatar_mid = userservice.getAvatar_mid(userName);
			avatarList.add(avatar_mid);
		}
		request.setAttribute("totalRows", pageBean.getTotalRows());
		request.setAttribute("totalPage", pageBean.getTotalPage());
		request.setAttribute("page", pageBean.getCurrentPage());
		request.setAttribute("pagesize", pageBean.getPageSize());
		request.setAttribute("hasNextPage", pageBean.getHasNextPage());
		request.setAttribute("texts", pageBean.getList());
		request.setAttribute("avatarList", avatarList);
		
		return SUCCESS;
	}
	
	//删除文章
	public String deleteText(){
		/**先判断权限，有权限才能删除
		 * */
		ActionContext actioncontext = ActionContext.getContext();
		Map session = actioncontext.getSession();
		String email = (String)session.get("email");
		int role = userservice.getRole(email);
		if(role==Constants.ROLE_EDITOR){
			if(textservice.deleteText(this.getTextid())){
				return SUCCESS;
			}else{
				return LOGIN;
			}
		}else{
			ActionContext actionContext = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
			request.setAttribute("deleteFail", "No permission");
			return LOGIN;
		}
	}
	
//setters and getters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
	public int getTextid() {
		return textid;
	}
	public void setTextid(int textid) {
		this.textid = textid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
