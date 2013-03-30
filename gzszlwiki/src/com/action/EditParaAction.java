package com.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.entity.Paragraph;
import com.entity.Text;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ParaService;
import com.service.TextService;

@Component("editpara")
public class EditParaAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private int paraid ;
	private int textid ;
	private String ptitle;
	private String content;
	private String title ;
	

	ParaService paraservice ;
	TextService textservice ;
	
	public TextService getTextservice() {
		return textservice;
	}
	@Resource(name="textservice")
	public void setTextservice(TextService textservice) {
		this.textservice = textservice;
	}
	public ParaService getParaservice() {
		return paraservice;
	}
	@Resource(name="paraservice")
	public void setParaservice(ParaService paraservice) {
		this.paraservice = paraservice;
	}

	//execute��������ת���޸Ķ���ҳ�棨editPara.jsp��ǰ������һ��ԭҳ��������ݣ�������ʾ
	public String execute()throws Exception{
		
		Paragraph para = paraservice.getParaById(this.getParaid());		
		ActionContext actionContext = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
		
		request.setAttribute("para", para);
		return "success";
	}
	
	//增加段落	
	public String addPara()throws Exception{
		//输入验证
		if(ptitle!=null&&!ptitle.equals("")&&content!=null&&!content.equals("")){
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			String lastEditor = (String)session.get("username");
			//跟新最后修改时间
			Date date = new Date();
			Timestamp lasttime = new Timestamp(date.getTime());
			//创建一个段落对象
			Paragraph paragraph = new Paragraph(textid,ptitle,content,1,"a",lasttime,lastEditor);
			Text text = textservice.viewText(textid);
			//把创建的段落对象添加到text里
			text.addPara(paragraph);
			
			text.setLasttime(lasttime);
			text.setLastEditor(lastEditor);
			//跟新text
			boolean add = textservice.updateText(text);
			
			HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
			Set<Paragraph> paras = text.getParagraphs();
			
			request.setAttribute("text", text);
			request.setAttribute("paras", paras);
			if(add){
				return SUCCESS;
			}else{
				return LOGIN ;
			}
		}else{
			
			return LOGIN ;
		}
		
	}
	
	//删除段落
	public String deletePara()throws Exception{
		
		if(paraservice.deletePara(paraid)){
			
			return SUCCESS;
		}else{
			return LOGIN;
		}
			
	}
	
	//修改段落
	public String updatePara ()throws Exception{
		if((content==null||content.equals(""))&&(ptitle==null||ptitle.equals(""))){
			//内容跟标题都为空，删掉这个段落
			if(paraservice.deletePara(paraid)){
				
				return SUCCESS;
			}else{
				return LOGIN;
			}
			
		}else{
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			
			Text text = textservice.viewText(textid);
			//�����޸�ʱ��
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			text.setLasttime(timestamp);
			//��������޸���	
			String username = (String)session.get("username");
			text.setLastEditor(username);
			
			//���¶����޸�
			Paragraph para = paraservice.getParaById(paraid);
			
			if(content==null||content.equals("")){
				content="";
			}
			para.setContent(content);
//			System.out.println("action__content:"+content);

			if(ptitle==""||ptitle.equals(null)){
				ptitle="";
			}
			para.setPtitle(ptitle);
					
			boolean update = textservice.updateText(text, para);
			if(update){
				
				return "success";
			}else{
				return "login";
			}
		}
		
	}
	
//setters and getters
	public int getParaid() {
		return paraid;
	}
	public void setParaid(int paraid) {
		this.paraid = paraid;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
