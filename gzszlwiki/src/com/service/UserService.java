package com.service;

import com.entity.User;

public interface UserService {
	int login (String email , String password);
	boolean register(String email ,String username, String password);
	boolean exit ();
	String[]  getUsernameAndRole (String email);
	int getRole(String email);
	public User getUserInf (String email);
	public boolean changPsw(String email,String newPassword);
	public boolean checkPassword (String email,String password);
	public boolean updateHeadImg (String email,String avatar_big,
			String avatar_mid,String avatar_sml);
	public String getAvatar_big(String userName);
	public String getAvatar_mid(String userName);
	public String getAvatar_sml(String userName);
}
