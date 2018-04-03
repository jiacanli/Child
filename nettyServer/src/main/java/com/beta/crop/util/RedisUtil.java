package com.beta.crop.util;

import org.apache.log4j.Logger;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private final static Logger Log = Logger.getLogger(RedisUtil.class);
	public final static String MESSAGE = "messsage";
	public final static String MESSAGE_TIME_POINTER = "time_pointer"; // 最后一次读取消息时间戳,增量读取
	private static JedisPool message_pool;
    private static JedisPool time_pointer_pool;
    private static JedisPoolConfig config = new JedisPoolConfig();
    private static String HOST = "localhost";
    static{
    	
        config.setMaxIdle(10); 
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(false); 
        message_pool = new JedisPool(config,HOST,7379,30000,"ljcc");
        time_pointer_pool = new JedisPool(config,HOST,8379,30000,"ljcc");
        Log.debug("reids pool init successfully");
        
        
    }
	
	public RedisUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static Jedis getRedisInstance(String type){
		if(message_pool==null||time_pointer_pool==null){
			return null;
		}
		if(type.equals(MESSAGE)){
			return  message_pool.getResource();
		}
		
		else if(type.equals(MESSAGE_TIME_POINTER)){
			return time_pointer_pool.getResource();
		}
		
		return null;
		
	}
	
	public static void releaseToPool(Jedis instance,String type) {
		if(type.equals(MESSAGE)) {
			
		}
		
		if(type.equals(MESSAGE_TIME_POINTER)) {
			
		}
		
	}
	
	
	

	
	

}
