package com.daoImpl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.dao.ParaDao;
import com.entity.Paragraph;

@Component("paradao")
public class ParaDaoImpl implements ParaDao {
	private HibernateTemplate hibernatetemplate = null;
	private SessionFactory sessionfactory ; 
	
	@Resource(name="sessionFactory")
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	
	public HibernateTemplate getHibernatetemplate() {
		if(hibernatetemplate ==null ){
			hibernatetemplate = new HibernateTemplate(sessionfactory);
		}
		return hibernatetemplate;
	}
	
	//�������
	public boolean save(Paragraph para) {
		// TODO Auto-generated method stub
		try{
			Session session = sessionfactory.openSession();
			session.save(para);
			return true;
		}catch(RuntimeException  e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	//��ݶ���id��ȡ�������
	public Paragraph getParaById(int paraid) {
		// TODO Auto-generated method stub
		Paragraph para = (Paragraph)this.getHibernatetemplate().find("from Paragraph p where p.paraid=?",paraid).get(0);
		return para;
	}
	
	public boolean changeParaStatus(Paragraph para ){
		try{
			this.getHibernatetemplate().saveOrUpdate(para);
			return true;
		}catch(RuntimeException e){
			e.printStackTrace();
			return false;
		}
	}

	public int getStatus(int ParaId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//删除段落
	public boolean deletePara(int paraid) {
		// TODO Auto-generated method stub
		try{
			Paragraph para = (Paragraph)this.getHibernatetemplate().find("from Paragraph p where p.paraid=?",paraid).get(0);
			this.getHibernatetemplate().delete(para);			
			return true;
		}catch(RuntimeException e){
			e.printStackTrace();
			return false;
		}
	}

}
