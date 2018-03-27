package com.beta.crop.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	public UdpClientHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket dp) throws Exception {
		// TODO Auto-generated method stub		
		System.out.println(dp.content().toString());
	}

}
