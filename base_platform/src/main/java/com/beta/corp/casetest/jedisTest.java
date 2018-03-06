package com.beta.corp.casetest;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class jedisTest {

	public jedisTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		JedisPoolConfig jconfig = new JedisPoolConfig();
		JedisPool jpool = new JedisPool(jconfig,"localhost",6379,3000,"123456");
		Jedis instance  = jpool.getResource();
		instance.set("test_Key", "test_Value");

		
		
		
	}

}
