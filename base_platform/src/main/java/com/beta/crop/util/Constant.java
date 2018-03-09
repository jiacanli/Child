package com.beta.crop.util;

public class Constant {
	public static final String USER_PREFIX = "200";
	public static final String FILE_PREFIX = "400";
	public Constant() {
		// TODO Auto-generated constructor stub
	}
	
	public static final class UserConstant{
	
		public static final String NAME_ALREADY_IN_USE = USER_PREFIX+"001";// 用户名已注册
		public static final String WRONG_PSWD = USER_PREFIX+"002"; // 密码错误
		public static final String ALREADY_SIGN_ID = USER_PREFIX+"003"; //用户已登录
		
				
	}
	
	public static final class FileConstant{
		public static final String WRONG_PARAMETER = FILE_PREFIX+"001"; // 缺少code,guid参数
		public static final String UPLOAD_FAIL = FILE_PREFIX +"002";
		
		
	}
	
	public static final class ProductConstant{
		
	}

}
