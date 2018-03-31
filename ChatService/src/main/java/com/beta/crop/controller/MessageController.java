package com.beta.crop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.crop.model.DataModel;
import com.beta.crop.model.MessageRequest;
import com.beta.crop.redis.model.Message;
import com.beta.crop.service.MessageService;
import com.beta.crop.util.ResultMapUtils;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService service;
	
	public MessageController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/send")
	public DataModel<Object> sendMsg(MessageRequest request){
		Message msg = transfer(request);
		return service.send(msg);
	}
	
	@RequestMapping("/recieve")
	public DataModel<Object> recieveMsg(HttpServletRequest request){
		String userid = request.getParameter("userid");
		Long timestamp = Long.parseLong(request.getParameter("gtime"));
		if(userid==null||timestamp==null){
			return ResultMapUtils.getFailResultMap(ResultMapUtils.PARAM_ERROR_KEY, 
					ResultMapUtils.PARAM_ERROR);
		}
		
		return service.recieve(userid, timestamp);
	}
	
	private Message transfer(MessageRequest request){
		Message msg=new Message();
		msg.setSender_id(request.getUserid());
		msg.setSender_name(request.getUsername());
		msg.setUser_icon(request.getUsericon());
		msg.setReciever_id(request.getRecieverid());
		msg.setContent(request.getData());
		msg.setTime_stamp(System.currentTimeMillis());		
	    msg.setGuid(request.getGuid());
		return msg;
	}

}
