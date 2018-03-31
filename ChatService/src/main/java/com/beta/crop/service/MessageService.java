package com.beta.crop.service;

import com.beta.crop.model.DataModel;
import com.beta.crop.redis.model.Message;

public interface MessageService {

	DataModel<Object> send(Message message);
	DataModel<Object> recieve(String userid,Long timestamp);
	
	
}
