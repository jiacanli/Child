package com.beta.crop.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.beta.crop.config.TcpConnectionInit;
import com.beta.crop.model.DataModel;
import com.beta.crop.redis.model.Message;
import com.beta.crop.service.MessageService;
import com.beta.crop.util.BufferUtil;
import com.beta.crop.util.RedisUtil;
import com.beta.crop.util.ResultMapUtils;
import com.beta.crop.util.SerialUtil;

import io.netty.buffer.Unpooled;
import redis.clients.jedis.Jedis;

@Service
public class MessageServiceImpl implements MessageService{

	public MessageServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public DataModel<Object> send(Message message) {
		// TODO Auto-generated method stub
		Jedis jedis_instance = RedisUtil.getRedisInstance(RedisUtil.MESSAGE); 
		Jedis jedis_timestamp = RedisUtil.getRedisInstance(RedisUtil.MESSAGE_TIME_POINTER);
		String reciever = message.getReciever_id();
		if(reciever==null){
			return ResultMapUtils.getFailResultMap(ResultMapUtils.MSG_RECIEVER_NULL_KEY, 
					ResultMapUtils.MSG_RECIEVER_NULL);
		}
		
		if(jedis_instance.exists(reciever.getBytes())){ //判断是否存在该用户
			//取出存消息得list
			byte[] msg_list_bytes = jedis_instance.get(reciever.getBytes());
			List<Message> msg_list = (List<Message>) SerialUtil.unserializeList(msg_list_bytes);
			//添加需要发送的新消息
			msg_list.add(message);
			//重新放入redis
			jedis_instance.set(reciever.getBytes(), SerialUtil.serializeList(msg_list));			
		}
		else{ // 不存在则新建
			List<Message> messages  = new ArrayList<Message>();
			messages.add(message);
			jedis_instance.set(reciever.getBytes(), SerialUtil.serializeList(messages));
		}
		
		
		jedis_instance.close();
		if(jedis_timestamp.exists(reciever)){
			byte[] reciever_timestamp_byte = jedis_timestamp.get(reciever.getBytes());
			informTcp(message.getSender_id(),(Long)SerialUtil.unserializeObj(reciever_timestamp_byte));
		}
		else{
			informTcp(message.getSender_id(),0L);
		}
		jedis_timestamp.close();
		return ResultMapUtils.getResultMap("发送成功", "");
	}

	public DataModel<Object> recieve(String userid, Long timestamp) {
		// TODO Auto-generated method stub
		Jedis jedis = RedisUtil.getRedisInstance(RedisUtil.MESSAGE);
		
		if(!jedis.exists(userid.getBytes())){
			return ResultMapUtils.getResultMap("");
		}
		byte[] message_bytes = jedis.get(userid.getBytes()); 
		List<Message> all_messages = (List<Message>) SerialUtil.unserializeList(message_bytes);
		List<Message> result = all_messages.stream().filter(message -> message.getTime_stamp()>=timestamp
				).collect(Collectors.toList());
		
		jedis.close();
		return ResultMapUtils.getResultMap(result);
	}
	
	private  void informTcp(String userid,Long gtime){
		JSONObject msg_body = new JSONObject();
		msg_body.put("gtime", gtime);
		msg_body.put("userid", userid);
		msg_body.put("type", 5);
		TcpConnectionInit.TcpChannel.writeAndFlush(BufferUtil.str2ByteBuf(msg_body.toJSONString()+"\r\n"));
		
	}
	


}
