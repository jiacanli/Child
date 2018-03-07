package com.beta.crop.service;

import org.json.JSONObject;

import com.beta.crop.model.DataModel;
import com.beta.crop.model.ParameterPrototype;
import com.beta.crop.model.User;

public interface UserService {

	DataModel<Object> Signin(JSONObject data);
	DataModel<Object> Signout(ParameterPrototype params);
	boolean Signup(JSONObject data);
	int update(User user);
	
	
		
}
