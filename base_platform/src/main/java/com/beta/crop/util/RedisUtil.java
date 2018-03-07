package com.beta.crop.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private  static JedisPoolConfig config = new JedisPoolConfig();
	private static JedisPool pool;
	
	static {
        
        config.setMaxIdle(10); 
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(false); 
        
		pool = new JedisPool(config,"localhost", 6379,30000,"ljcc");
		
		
	}
	public RedisUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Jedis getRedisIns() {
		if(pool!=null) {
			return pool.getResource();
		}
		return null;
	}
	
	
	
	
}
