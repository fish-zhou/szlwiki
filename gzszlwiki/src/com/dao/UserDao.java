package com.dao;

import com.entity.User;

public interface UserDao {
	boolean hasUser (String email);
	boolean checkPassword (String email,String password);
	String[]  getUsernameAndRole (String email);
	void save(User user);
	public  int getRole(String email);
	public User getUserInf (String email);
	public boolean changPsw(String email,String newPassword);
	//跟新用户头像
	public boolean updateHeadImg (String email,String avatar_big,
			String avatar_mid,String avatar_sml);
	public String getAvatar_big(String userName);
	public String getAvatar_mid(String userName);
	public String getAvatar_sml(String userName);
}
