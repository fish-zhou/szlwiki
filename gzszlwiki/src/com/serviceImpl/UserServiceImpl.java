package com.serviceImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dao.UserDao;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.service.UserService;

@Component("userservice")
public class UserServiceImpl implements UserService {
	private UserDao userdao ;
	
	@Resource(name="userdao")
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	//锟斤拷录锟斤拷锟斤拷
	public int login(String email, String password) {
		// TODO Auto-generated method stub
		boolean hasuser = userdao.hasUser(email);
		if(hasuser==true){
			boolean checkpassword = userdao.checkPassword(email, password);
			if(checkpassword==true){
				return 2;              //閭锛屽瘑鐮侀兘姝ｇ‘锛岃繑鍥�
			}else{
				return 1;              //閭姝ｇ‘锛屽瘑鐮侀敊璇紝杩斿洖1
			}
		}else{
			return 0;                  //閭閿欒锛岃繑鍥�
		}
	}
	
	//注锟结处锟斤拷
	public boolean register(String email ,String username, String password){
		boolean hasuser = userdao.hasUser(email);
		//锟介看锟斤拷锟斤拷锟角凤拷注锟斤拷锟�
		if(hasuser==true){
			return false;
		}else{
			User user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(1);
			userdao.save(user);
			return true;
		}
	}
	
	//锟剿筹拷锟斤拷录
	public boolean exit(){
		try{
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			session.clear();
			return true;
		}catch(RuntimeException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//锟斤拷锟斤拷没锟斤拷锟斤拷权锟斤拷
	public String[]  getUsernameAndRole (String email){
		String[] nameAndRole = userdao.getUsernameAndRole(email);
		return nameAndRole;
	}
	
	public  int getRole(String email){
		return userdao.getRole(email);
	}
	
	//鑾峰彇鐢ㄦ埛淇℃伅
	public User getUserInf (String email){
		return userdao.getUserInf(email);
	}
	
	//淇敼鐢ㄦ埛瀵嗙爜
	public boolean changPsw(String email,String newPassword){
		return userdao.changPsw(email, newPassword);
	}
	
	public boolean checkPassword (String email,String password){
		return userdao.checkPassword(email, password);
	}
	
	//璺熸柊鐢ㄦ埛澶村儚
	public boolean updateHeadImg (String email,String avatar_big,
			String avatar_mid,String avatar_sml){
		return userdao.updateHeadImg(email, avatar_big, avatar_mid, avatar_sml);
	}
	
	public String getAvatar_big(String userName){
		return userdao.getAvatar_big(userName);
	}
	public String getAvatar_mid(String userName){
		return userdao.getAvatar_mid(userName);
	}
	public String getAvatar_sml(String userName){
		return userdao.getAvatar_sml(userName);
	}
}
