package com.beta.crop.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class ServerInstance {

	public ServerInstance() {
		// TODO Auto-generated constructor stub
	}

//    private static final String IP = "13.125.234.135";  
	private static Logger log = LoggerFactory.getLogger(ServerInstance.class);
    private static final int PORT = 50051;  
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
        ServerBootstrap b = new ServerBootstrap();  
        b.group(bossGroup, workerGroup);  
        b.channel(NioServerSocketChannel.class);  
        b.childOption(ChannelOption.TCP_NODELAY, true);
        b.childHandler(new ChannelInitializer<SocketChannel>() {  
            @Override  
            public void initChannel(SocketChannel ch) throws Exception {  
                ChannelPipeline pipeline = ch.pipeline();  
//                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
//                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));  
//                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new LineBasedFrameDecoder(1024));
                pipeline.addLast(new StringDecoder());
                pipeline.addLast(new ServerHandler());
                  
                
            }  
        });  
  
        b.bind(PORT).sync();  
        
    }  
  
    protected static void shutdown() {  
        workerGroup.shutdownGracefully();  
        bossGroup.shutdownGracefully();  
    }  
	
	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");
		try {
			new ServerInstance().run();
			log.info("server start ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
