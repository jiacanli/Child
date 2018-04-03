package com.beta.crop.util;

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
		Jedis time_jedis = RedisUtil.getRedisInstance(RedisUtil.MESSAGE_TIME_POINTER);
		if(!time_jedis.exists(userid)){
			return new RegisgerResponse("5", 0L);			
		}	
		byte[] gtime_bytes = time_jedis.get(userid.getBytes());
		Long gtime = (Long) SerialUtil.unserializeObj(gtime_bytes);		
		time_jedis.close();
		
		return new RegisgerResponse("5", gtime);
	}
	
	public static  void message_confirm(String userid,Long timestamp){
		//更新最新的读取时间
		Jedis time_jedis = RedisUtil.getRedisInstance(RedisUtil.MESSAGE_TIME_POINTER);
		time_jedis.set(userid.getBytes(), SerialUtil.serializeObj(timestamp));
		time_jedis.close();
		
	}
	


}
