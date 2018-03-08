package com.beta.crop.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

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
	@RequestMapping("/service")
	public DataModel<Object> singup(ParameterPrototype params) {
		int code = Integer.parseInt(params.getCode());
		switch (code) {
		case 1:
			return signin(params);
		case 2:
			JSONObject data = new JSONObject(params.getData());
			boolean result = service.Signup(data);			
			return result==true?ResultMapUtils.getResultMap("注册成功", ""):
				ResultMapUtils.getFailResultMap("201", "注册失败");
		case 3:
			return signout(params);
		default:
			return ResultMapUtils.getFailResultMap("201", "code参数错误");
		}

		
	}
	
	private DataModel<Object> signin(ParameterPrototype params) {
		JSONObject data = new JSONObject(params.getData());
		data.put("deviceid", params.getDeviceid());
		data.put("ip", "_ip");
		return service.Signin(data);
	}
	
	
	private DataModel<Object> signout(ParameterPrototype params) {
		return service.Signout(params);
	}

}
