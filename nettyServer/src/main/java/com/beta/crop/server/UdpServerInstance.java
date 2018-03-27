package com.beta.crop.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class UdpServerInstance {

	public UdpServerInstance() {
		// TODO Auto-generated constructor stub
	}
	
    private static final int PORT = 9686;  
    /** 用于分配处理业务线程的线程组个数 */  
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认  
    /** 业务出现线程大小 */  
    protected static final int BIZTHREADSIZE = 4;  
    /* 
     * NioEventLoopGroup实际上就是个线程池, 
     * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件, 
     * 每一个NioEventLoop负责处理m个Channel, 
     * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel 
     */  
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);  
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);  
  
    protected static void run() throws Exception {  
        Bootstrap b = new Bootstrap();  
        b.group(bossGroup);  
        b.channel(NioDatagramChannel.class);  
        b.option(ChannelOption.SO_BROADCAST, true);
        b.handler(new UdpServerHandler()); 
  
        b.bind(PORT).sync();  
        
    }  
    
    
	public static void main(String[] args) {
		try {
			new UdpServerInstance().run();
			System.out.println("server start");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
