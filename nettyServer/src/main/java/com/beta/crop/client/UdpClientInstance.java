package com.beta.crop.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpClientInstance {
    public static String HOST = "118.144.140.255";  
    public static int PORT = 9686;  
  
    public static Bootstrap bootstrap = getBootstrap();  
    public static Channel channel = getChannel(HOST, PORT);  
	public UdpClientInstance() {
		// TODO Auto-generated constructor stub
	}
    public static final Bootstrap getBootstrap() {  
        EventLoopGroup group = new NioEventLoopGroup();  
        Bootstrap b = new Bootstrap();  
        b.group(group).channel(NioDatagramChannel.class);  
        b.handler(new ChannelInitializer<Channel>() {  
            @Override  
            protected void initChannel(Channel ch) throws Exception {  
                ChannelPipeline pipeline = ch.pipeline();  
                pipeline.addLast("handler", new UdpClientHandler());  
            }  
        });  
//        b.childoption(ChannelOption.SO_KEEPALIVE, true);  
        return b;  
    }  
  
    public static final Channel getChannel(String host, int port) {  
        Channel channel = null;  
        try {  
            channel = bootstrap.bind(host, port).sync().channel();  
        } catch (Exception e) {  
            
           e.printStackTrace();
        }  
        return channel;  
    }  
  
    public static void sendMsg(String msg) throws Exception {  
        if (channel != null) {  
            channel.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes())).sync();  
        } else {  
            System.out.println("channel null");
        }  
    } 
    
    public static void main(String[] args) {
    	try {
			UdpClientInstance.sendMsg("ss");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
