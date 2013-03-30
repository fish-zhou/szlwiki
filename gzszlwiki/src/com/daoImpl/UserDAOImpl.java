package com.daoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.dao.UserDao;
import com.entity.User;

@Component("userdao")
public class UserDAOImpl extends JdbcDaoSupport implements UserDao {
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
	
	public HibernateTemplate getHibernatetemplate() {
		if(hibernatetemplate ==null ){
			hibernatetemplate = new HibernateTemplate(sessionfactory);
		}
		return hibernatetemplate;
	}

	public void setHibernatetemplate(HibernateTemplate hibernatetemplate) {
		this.hibernatetemplate = hibernatetemplate;
	}

	//锟斤拷锟斤拷没锟斤拷锟絜mail锟斤拷询锟角凤拷锟窖达拷锟节革拷锟矫伙拷
	public boolean hasUser(String email) {
		//锟斤拷锟斤拷i锟斤拷锟节憋拷示锟角凤拷锟斤拷锟捷匡拷锟斤拷取锟斤拷锟斤拷锟斤拷锟�
		int i = this.getHibernatetemplate().find("from User as u where u.email=?",new String(email)).size();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	//锟斤拷锟斤拷没锟斤拷锟斤拷锟斤拷锟斤拷欠锟斤拷锟饺�
	public boolean checkPassword(String email,String password) {
		//锟斤拷锟斤拷i锟斤拷锟节憋拷示锟角凤拷锟斤拷锟捷匡拷锟斤拷取锟斤拷锟斤拷锟斤拷锟�
		int i = this.getHibernatetemplate().find("from User as u where u.email=?and u.password=?",new String[]{email,password}).size();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	//锟斤拷锟斤拷没锟斤拷锟斤拷锟斤拷取锟矫伙拷锟斤拷锟饺拷锟�
	public String[] getUsernameAndRole (String email) {
		String [] nameAndRole = new String [2];
		User user = (User)this.getHibernatetemplate().find("from User as u where u.email=?",new String(email)).get(0);		
		nameAndRole[0] = user.getUsername();
		nameAndRole[1] = Integer.toString(user.getRole());
		
		return nameAndRole;
	}
	
	public  int getRole(String email){
		User user = (User)this.getHibernatetemplate().find("from User as u where u.email=?",new String(email)).get(0);
		return user.getRole();
	}
	
	public void save(User user) {
		// TODO Auto-generated method stub
		getHibernatetemplate().save(user);

	}
	
	//鑾峰彇鐢ㄦ埛淇℃伅
	public User getUserInf (String email){
		User user = (User)this.getHibernatetemplate().find("from User as u where u.email=?",new String(email)).get(0);
		return user;
	}
	
	//淇敼鐢ㄦ埛瀵嗙爜
	public boolean changPsw(String email,String newPassword){
		User user = (User)this.getHibernatetemplate().find("from User as u where u.email=?",new String(email)).get(0);
		user.setPassword(newPassword);
		try{
			getHibernatetemplate().saveOrUpdate(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**璺熸柊鐢ㄦ埛澶村儚
	 * */
	public boolean updateHeadImg (String email,String avatar_big,
			String avatar_mid,String avatar_sml){
		try{
			//鑾峰彇鐢ㄦ埛id
			String sql = " select u.userid " +
					" from user as u " +
					" where u.email = ?";
			final int userid = this.getJdbcTemplate().queryForInt(sql,new Object[]{email});
			
			final String avatar_sml_f = avatar_sml;
			final String avatar_mid_f = avatar_mid;
			final String avatar_big_f = avatar_big;
			if(userid>0){
				//璁板綍澶村儚鍥剧墖鍦板潃
				String sql1 = " insert into avatar(imageurl,imagetype,userid) " +
						" values(?,?,?)";
				this.getJdbcTemplate().batchUpdate(sql1, new BatchPreparedStatementSetter(){
					public void setValues(java.sql.PreparedStatement ps,
							int i) throws SQLException {
						String imageurl = "";
						int imagetype = 0;
						if (0 == i) {
							// 灏忓浘-imagetype = 1
							imageurl = avatar_sml_f;
							imagetype = 1;
						} else if (1 == i) {
							// 涓浘-imagetype = 2
							imageurl = avatar_mid_f;
							imagetype = 2;
						} else {
							// 澶у浘-imagetype = 3;
							imageurl = avatar_big_f;
							imagetype = 3;
						}
						ps.setString(1, imageurl);
						ps.setInt(2, imagetype);
						ps.setInt(3, userid);
					} 
					public int getBatchSize(){ 
					    return 3; 
					}
				});
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String getAvatar_big(String userName){
		try{
			String sql = " select a.imageurl " +
					" from avatar a " +
					" inner join user u on u.userid = a.userid " +
					" where u.userName = ? and a.imagetype = ? ";
			List temp = this.getJdbcTemplate().queryForList(sql, new Object[]{userName,3});
			if(temp!=null&&temp.size()>0){
				Map m = (Map)temp.get(0);
				return (String)m.get("imageurl");
			}else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	public String getAvatar_mid(String userName){
		try{
			String sql = " select a.imageurl " +
					" from avatar a " +
					" inner join user u on u.userid = a.userid " +
					" where u.userName = ? and a.imagetype = ? ";
			List temp = this.getJdbcTemplate().queryForList(sql, new Object[]{userName,2});
			if(temp!=null&&temp.size()>0){
				Map m = (Map)temp.get(0);
				return (String)m.get("imageurl");
			}else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	public String getAvatar_sml(String userName){
		try{
			String sql = " select a.imageurl " +
					" from avatar a " +
					" inner join user u on u.userid = a.userid " +
					" where u.userName = ? and a.imagetype = ? ";
			List temp = this.getJdbcTemplate().queryForList(sql, new Object[]{userName,1});
			if(temp!=null&&temp.size()>0){
				Map m = (Map)temp.get(0);
				return (String)m.get("imageurl");
			}else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
}
