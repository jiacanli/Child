package com.beta.crop.server;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {
		private static  final Map<String, Channel> cache = new HashMap<String, Channel>();
	public ServerHandler() {
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//		// TODO Auto-generated method stub
//		 System.out.println("revieve msg "+msg);
//		 ctx.channel().writeAndFlush("server accepted msg:" + msg);  
//	}
	
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
//				super.channelRead(arg0, arg1);
				System.out.println("服务端接受消息");
				ByteBuf buf = (ByteBuf) arg1;
				byte[] bytes = new byte[buf.readableBytes()];
				buf.readBytes(bytes);
				InetSocketAddress ori = (InetSocketAddress) arg0.channel().remoteAddress();
				String msg = new String(bytes);
//				String msg = (String)arg1;
				System.out.println("服务端接受消息["+msg+"] from "+ori);				
				String string = "recieved";
				arg0.writeAndFlush(Unpooled.copiedBuffer(string.getBytes()));//发送到客户端
				
				String[] splited = msg.split(",");
				if(splited[0].equals("1")){
					cache.put(splited[1], arg0.channel());
					
				}
				
				else if(splited[0].equals("2")){
					Channel channel = cache.get(splited[1]);
					if(channel!=null){
						channel.writeAndFlush(Unpooled.copiedBuffer(splited[2].getBytes()));
					}
					
				}
				
						
	}
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {    	
        System.out.println("channel disconnected by client ");
    	ctx.fireChannelInactive();
    	cache.values().remove(ctx.channel());
        
    }
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
    	InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
//        String  clientIP = insocket.getAddress().getHostAddress();
        
    	System.out.println(insocket+" connected");
    		
//        ctx.channel().writeAndFlush("serer res");
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
