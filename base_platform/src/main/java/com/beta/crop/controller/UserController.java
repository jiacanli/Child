package com.beta.crop.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.crop.model.DataModel;
import com.beta.crop.model.ParameterPrototype;
import com.beta.crop.service.UserService;
import com.beta.crop.util.ResultMapUtils;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("/signup")
	public DataModel<Object> singup(ParameterPrototype params) {
		JSONObject data = new JSONObject(params.getData());
		boolean result = service.Signup(data);
		
		return result==true?ResultMapUtils.getResultMap("注册成功", ""):
			ResultMapUtils.getFailResultMap("201", "注册失败");
		
	}
	@RequestMapping("/signin")
	public DataModel<Object> signin(ParameterPrototype params) {
		JSONObject data = new JSONObject(params.getData());
		data.put("deviceid", params.getDeviceid());
		data.put("ip", "_ip");
		return service.Signin(data);
	}
	
	@RequestMapping("/signout")
	public DataModel<Object> signout(ParameterPrototype params) {
		return service.Signout(params);
	}

}
