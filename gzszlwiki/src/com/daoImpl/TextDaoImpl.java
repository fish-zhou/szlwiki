package com.daoImpl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import util.PageBean;

import com.dao.TextDao;
import com.entity.Text;

@Component("textdao")
public class TextDaoImpl extends JdbcDaoSupport implements TextDao {
	
	private HibernateTemplate hibernatetemplate = null;
	private SessionFactory sessionfactory ; 
	@Resource(name="jdbcTemplate")
	public void setJt(JdbcTemplate jt) {
		super.setJdbcTemplate(jt);
	}
	@Resource(name="sessionFactory")
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public HibernateTemplate getHibernatetemplate() {
		if(hibernatetemplate ==null ){
			hibernatetemplate = new HibernateTemplate(sessionfactory);
		}
		return hibernatetemplate;
	}
	
	//wiki首页请求数据
	public PageBean viewText(PageBean pageBean) {
		
		//List<Text> list = this.getHibernatetemplate().find("from Text t order by t.lasttime desc");
		try {  	
			//获取总页数
			String sql = " select count(t.textid) " +
					" from Text t ";
			int totalRows = this.getJdbcTemplate().queryForInt(sql);
			
			pageBean.setTotalRows(totalRows);
			pageBean.setTotalPage(pageBean.countTotalPage(totalRows));
			pageBean.countHasNextPage();
			pageBean.countStart(pageBean.getTotalPage());
			
			final int start = pageBean.getStart();
			final int pageSize = pageBean.getPageSize();
			
			if(pageBean.getCurrentPage() <= pageBean.getTotalPage()){
				List<Text> list = this.getHibernatetemplate().execute(
						new HibernateCallback<List<Text>>() {
							public List<Text> doInHibernate(Session session)
									throws HibernateException, SQLException {
								// System.out.println("执锟叫凤拷页锟斤拷锟斤拷");
								Query query = session
										.createQuery("from Text t order by t.lasttime desc");
								query.setFirstResult(start).setFetchSize(pageSize);
								query.setMaxResults(pageSize);
								return (List<Text>) query.list();
							}
						});
				pageBean.setList(list);
			}
			return pageBean;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}
		//return list;
	}
	//锟斤拷锟絫extid锟斤拷锟斤拷取锟斤拷锟斤拷
	public Text getText(int textid) {
		// TODO Auto-generated method stub
		List<Text> list = this.getHibernatetemplate().find("from Text t where t.textid=?",textid);
		if(list!=null&&list.size()>0){
			Text text = list.get(0);
			return text;
		}else{
			return null;
		}		
		
	}
	
	//锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷
	public boolean save(Text text) {
		// TODO Auto-generated method stub
		try{
			this.getHibernatetemplate().saveOrUpdate(text);
			return true;
		}catch(RuntimeException  e){
			e.printStackTrace();
			return false;
		}
	}
	
	//锟斤拷锟铰对讹拷锟戒、锟斤拷锟接碉拷锟睫革拷锟睫革拷
	public boolean updateText(Text text){
		try{			
			this.getHibernatetemplate().saveOrUpdate(text);			
			return true;
		}catch(RuntimeException  e){
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean deleteText(int textid) {
		try{
			Text text =(Text)this.getHibernatetemplate().find("from Text t where t.textid=?",textid).get(0);
			this.getHibernatetemplate().delete(text);
			return true;
		}catch(RuntimeException e){
			e.printStackTrace();
			return false;
		}
	}
	
}
