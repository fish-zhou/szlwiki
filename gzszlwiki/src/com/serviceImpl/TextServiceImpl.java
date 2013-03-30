package com.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import util.PageBean;

import com.dao.TextDao;
import com.entity.Paragraph;
import com.entity.Text;
import com.service.TextService;

@Component("textservice")
public class TextServiceImpl implements TextService {
	TextDao textdao ;
		
	@Resource(name="textdao")
	public void setTextdao(TextDao textdao) {
		this.textdao = textdao;
	}

	public Text viewText(int textid) {
		
		Text text =  textdao.getText(textid);
		return text;
	}
	
	//锟介看锟斤拷锟斤拷锟斤拷锟接ｏ拷实锟斤拷只取锟斤拷20锟斤拷锟斤拷示锟斤拷
	public PageBean viewText(PageBean pageBean) {
		// TODO Auto-generated method stub
		try
		{
			pageBean = textdao.viewText(pageBean);
	
		}catch(RuntimeException e){
//			System.out.println("执锟斤拷viewAllText锟斤拷锟斤拷");
			e.printStackTrace();
		}
		return pageBean;
		
	}
	
	public boolean updateText (Text text){
		boolean update = textdao.updateText(text);
		return update; 
	}
	
	//锟睫改革拷锟铰讹拷锟戒，同时锟斤拷锟斤拷锟斤拷锟接碉拷锟斤拷锟斤拷薷锟斤拷烁锟绞憋拷锟�
	//要锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷匣锟斤拷锟绞憋拷锟斤拷远锟斤拷锟轿拷陆锟揭伙拷锟斤拷碌亩锟斤拷锟�
	public boolean updateText (Text text,Paragraph para){
		Set<Paragraph> paras = new HashSet<Paragraph>();
		paras.add(para);		
		text.setParagraphs(paras);			
		
		boolean update = textdao.updateText(text);
		return update;
	}

	public boolean addText(Text text) {
		
		return textdao.save(text);
	}
	

	public boolean deleteText(int textid) {
		
		return textdao.deleteText(textid);
	}
}
