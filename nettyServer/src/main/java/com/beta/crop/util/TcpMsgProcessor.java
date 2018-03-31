package com.beta.crop.util;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.text.StyledEditorKit.StyledTextAction;

import com.alibaba.fastjson.JSONObject;
import com.beta.crop.redis.model.Message;
import com.beta.crop.redis.model.RegisgerResponse;

import redis.clients.jedis.Jedis;

public class TcpMsgProcessor {

	public TcpMsgProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	public static RegisgerResponse register(String userid){
		Jedis msg_jedis = RedisUtil.getRedisInstance(RedisUtil.MESSAGE);
		Jedis time_jedis = RedisUtil.getRedisInstance(RedisUtil.MESSAGE_TIME_POINTER);
//		time_jedis.set("ss", 12L);
		if(!time_jedis.exists(userid)){
			return new RegisgerResponse(0, 0L);
			
		}		
		byte[] last_time_stamp_seria = time_jedis.get(userid.getBytes());
		final Long last_time_stamp = (Long) SerialUtil.unserializeObj(last_time_stamp_seria);
		byte[] message_seria = msg_jedis.get(userid.getBytes());
		List<Message> messages = (List<Message>) SerialUtil.unserializeList(message_seria);
		
		List<Message> unread_msg = messages.stream()
				.filter(message -> message.getTime_stamp()>=last_time_stamp).collect(Collectors.toList());
		
		
		msg_jedis.close();
		
		return new RegisgerResponse(unread_msg.size(), last_time_stamp);
	}
	
	public static  void message_confirm(String userid,Long timestamp){
		//更新最新的读取时间
		Jedis time_jedis = RedisUtil.getRedisInstance(RedisUtil.MESSAGE_TIME_POINTER);
		time_jedis.set(userid.getBytes(), SerialUtil.serializeObj(timestamp));
		time_jedis.close();
	}
	


}
