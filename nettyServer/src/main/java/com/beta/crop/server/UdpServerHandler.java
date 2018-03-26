package com.beta.crop.server;

import java.net.InetSocketAddress;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	public UdpServerHandler() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("udp revieve "+msg.content().toString());
		ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("to client udp".getBytes()),msg.sender()));
		
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
    
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
    	InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        String  clientIP = insocket.getAddress().getHostAddress();
    	System.out.println(clientIP+" udp  connected");
    		
//        ctx.channel().writeAndFlush("serer res");
    }
}
