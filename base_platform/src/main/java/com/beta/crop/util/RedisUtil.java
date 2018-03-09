package com.beta.crop.util;


import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private  static JedisPoolConfig config = new JedisPoolConfig();
//	private static Properties properties = PropertiesFileUtil.LoadProperties("general.properties");
	private static Properties pUtil = PropertiesFileUtil.LoadProperties("general.properties");
	private static JedisPool pool;
	
	static {
        
        config.setMaxIdle(10); 
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(false); 
        
		pool = new JedisPool(config,pUtil.getProperty("redis_server"), Integer.parseInt(pUtil.getProperty("port")),30000);
		
		
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
