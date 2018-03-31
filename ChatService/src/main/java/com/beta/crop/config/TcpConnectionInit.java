package com.beta.crop.config;

import java.net.InetSocketAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.beta.crop.controller.*;
public class TcpConnectionInit implements ServletContextListener{
	
	private static final Logger log =  Logger.getLogger(TcpConnectionInit.class);
	public static  Channel TcpChannel;
	public static final String HOST = "";
	public static final int PORT =50051 ;
	public TcpConnectionInit() {
		// TODO Auto-generated constructor stub
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
        EventLoopGroup group = new NioEventLoopGroup();  
        Bootstrap b = new Bootstrap();  
        b.group(group).channel(NioSocketChannel.class);  
        b.handler(new ChannelInitializer<Channel>() {  
            @Override  
            protected void initChannel(Channel ch) throws Exception {  
                ChannelPipeline pipeline = ch.pipeline();    
                pipeline.addLast("handler", new ClinetHandler());  
            }  
        }); 
        
        b.option(ChannelOption.SO_KEEPALIVE, true);  
        b.option(ChannelOption.TCP_NODELAY, true);
        
        b.bind(PORT);
        try {
			ChannelFuture future = b.connect(new InetSocketAddress(PORT)).sync();
			TcpChannel = future.channel();
			log.debug("连接成功");
//			TcpChannel.closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
