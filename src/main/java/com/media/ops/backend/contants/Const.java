package com.media.ops.backend.contants;


public class Const {

	public static final String CURRENT_USER="currentUser";
	public static final String USERNAME = "username";
	public static final String EMAIL = "email";

	public interface Role{
		int ROLE_HOST=0;  //直播员
		int ROLE_ADMIN=1; //后台管理员
	}
	
}
