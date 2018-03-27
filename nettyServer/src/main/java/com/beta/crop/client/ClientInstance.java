package com.beta.crop.client;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.ChannelPipeline;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.nio.NioSocketChannel;  
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;  
import io.netty.handler.codec.LengthFieldPrepender;  
import io.netty.handler.codec.string.StringDecoder;  
import io.netty.handler.codec.string.StringEncoder;  
import io.netty.util.CharsetUtil;  
public class ClientInstance {

	public ClientInstance() {
		// TODO Auto-generated constructor stub
	}
	
//    public static String HOST = "13.124.243.54";  
    public static String HOST = "localhost";  
    public static int PORT = 50051;  
  
//    public static Bootstrap bootstrap = getBootstrap();  
//    public static Channel channel = getChannel(HOST, PORT);  
  
    /** 
     * 初始化Bootstrap 
     */  
    public static final void getBootstrap() {  
        EventLoopGroup group = new NioEventLoopGroup();  
        Bootstrap b = new Bootstrap();  
        b.group(group).channel(NioSocketChannel.class);  
        b.handler(new ChannelInitializer<Channel>() {  
            @Override  
            protected void initChannel(Channel ch) throws Exception {  
                ChannelPipeline pipeline = ch.pipeline();  
//                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
//                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));  
//                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));  
//                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));  
                pipeline.addLast("handler", new ClinetHandler());  
            }  
        });  
        b.option(ChannelOption.SO_KEEPALIVE, true);  
        b.bind(3000);
        try {
			Channel channel = b.connect(HOST, PORT).sync().channel();
			
			channel.writeAndFlush(Unpooled.copiedBuffer(("{\"userid\":\"123\",\"type\":\"1\"}"+"\r\n").getBytes()));
//			System.out.println("1 end");
//			System.out.println(channel.bytesBeforeUnwritable());
//			channel.writeAndFlush(Unpooled.copiedBuffer(("2,2,132"+"\r\n").getBytes()));
//			System.out.println(channel.isWritable());
//			System.out.println(channel.bytesBeforeUnwritable());
//			Thread.sleep(500);
//			channel.writeAndFlush(Unpooled.copiedBuffer(("2,2,132"+"\r\n").getBytes()));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return ; 
    }  
  
//    public static final Channel getChannel(String host, int port) {  
//        Channel channel = null;  
//        try {  
//            channel = bootstrap.connect(host, port).sync().channel();  
//        } catch (Exception e) {  
//            
//           e.printStackTrace();
//        }  
//        return channel;  
//    }  
  
//    public static void sendMsg(ByteBuf msg) throws Exception {  
//        if (channel != null) {  
//            channel.writeAndFlush(msg).sync();  
//        } else {  
//            System.out.println("");
//        }  
//    } 
    
    
    public static void main(String[] args) throws Exception {  
        try {  
           
           
//            	ClientInstance.sendMsg(Unpooled.copiedBuffer("2,2,srtffff".getBytes()));
//            	ClientInstance.sendMsg(Unpooled.copiedBuffer("2,2,s222".getBytes())); 
//            	ClientInstance.sendMsg(Unpooled.copiedBuffer("2,2,s3333fff".getBytes())); 
        	ClientInstance.getBootstrap();
      
             
        } catch (Exception e) {  
            e.printStackTrace();
        }  
    }  

}
