package com.beta.crop.server;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beta.crop.redis.model.RegisgerResponse;
import com.beta.crop.util.BufferUtil;
import com.beta.crop.util.TcpConstant;
import com.beta.crop.util.TcpMsgProcessor;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {
		public static  final Map<String, Channel> cache = new ConcurrentHashMap<String, Channel>();
		private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);
		
	public ServerHandler() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 
	 * 注册
	 * ----------------------
	 * userid
	 * type(1:注册  2:心跳 3:读取消息回执 4:登出 5:消息转发)
	 * ----------------------
	 * 
	 * 登出
	 * ----------------------
	 * userid
	 * type
	 * 
	 * 
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */

	
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
		
		InetSocketAddress ori = (InetSocketAddress) arg0.channel().remoteAddress();
		String msg = (String) arg1;
		log.info("服务端接受消息[" + msg + "] from " + ori);
		JSONObject json_msg = JSONObject.parseObject(msg);
		String userid = json_msg.getString("userid");
		int type = Integer.parseInt(json_msg.getString("type"));
		switch (type) {
		case TcpConstant.TCP_REGISTER:
			cache.put(userid, arg0.channel());
			RegisgerResponse response = TcpMsgProcessor.register(userid);
			arg0.writeAndFlush(BufferUtil.str2ByteBuf(JSON.toJSONString(response)+"\r\n"));
			break;
		case TcpConstant.TCP_HEARTBEAT: // 心跳
			log.info("心跳");
			break;
		case TcpConstant.TCP_MSG_CONFIRM: // 消息回执
			Long timestamp = Long.parseLong(json_msg.getString("rtime"));
			TcpMsgProcessor.message_confirm(userid,timestamp);
			break;
		case TcpConstant.TCP_LOGOUT:
			Channel channel = cache.get(userid);
			if(channel!=null){
				channel.deregister();
				channel.disconnect();
				cache.remove(userid);
			}
			break;
		case TcpConstant.TCP_MSG_TRANSFER:
			log.info("message transfer");
			transfer(userid, json_msg.getLong("gtime"));
		default:
			break;
		}

//		String string = "recieved";
//		arg0.writeAndFlush(Unpooled.copiedBuffer(string.getBytes()));// 发送到客户端

	}
	
	private void transfer(String userid,Long timestamp) {
		if(cache.containsKey(userid)) {
			log.info("转发给用户 :"+userid);
			JSONObject object = new JSONObject();
			object.put("tyep", "5");
			object.put("gtime", timestamp);
			cache.get(userid).writeAndFlush(BufferUtil.str2ByteBuf(object.toJSONString()+"\r\n"));		
		}
		
		
	}
	
	
	
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {    	
        System.out.println("channel ["+ctx.channel().remoteAddress()+"] disconnected by client ");
    	ctx.fireChannelInactive();
    	cache.values().remove(ctx.channel());
        
    }
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
    	InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        
    	System.out.println(insocket+" connected at "+new Date());
    		
    }
    
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        System.out.println("exection");
        cause.printStackTrace();
        ctx.close();  
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("msg "+msg);
		
	}


	
	
	

}
